package learning.programming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class GameImpl implements Game {

    // == Constants ==
    private static final Logger log = LoggerFactory.getLogger(GameImpl.class);


    // == Init ==
    @PostConstruct
    @Override
    public void reset() {
        number = numberGenerator.next();
        guess = 0;
        remainingGuesses = guessCount;
        smallest = 0;
        biggest = numberGenerator.getMaxNumber();
        log.debug("The New Number Gen by PostConstruct is {}", number);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("preDestroy is called.");
    }

    // == Fields ==
    @Autowired
    private NumberGenerator numberGenerator;
    private int number;
    private int guess;
    @Autowired
    @GussCount
    private int guessCount;
    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean validNumberRange = true;

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int getGuess() {
        return guess;
    }

    @Override
    public void setGuess(int guess) {
        this.guess = guess;
    }

    @Override
    public int getSmallest() {
        return smallest;
    }

    @Override
    public int getBiggest() {
        return biggest;
    }

    @Override
    public int getRemainingGuesses() {
        return remainingGuesses;
    }

    @Override
    public int getGuessCount() {
        return guessCount;
    }

    @Override
    public void check() {
        checkValidNumberRange();
        if (validNumberRange) {
            if (guess > number) {
                biggest = guess - 1;
            }
            if (guess < number) {
                smallest = guess + 1;
            }
            remainingGuesses--;
        }
    }

    @Override
    public boolean isValidNumberRange() {
        return validNumberRange;
    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }

    // == private Checking Method ==
    private void checkValidNumberRange() {
        validNumberRange = (guess >= smallest) && (guess <= biggest);
    }
}
