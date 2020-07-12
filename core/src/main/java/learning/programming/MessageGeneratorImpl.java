package learning.programming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class MessageGeneratorImpl implements MessageGenerator {
    // == Constants ==
    private static final Logger log = LoggerFactory.getLogger(MessageGeneratorImpl.class);

    @Autowired
    private Game game;

    @PostConstruct
    public void init(){
        log.info("PostConstruct is Called");
        log.info("game = {}", game);
    }

    // == Public Methods ==
    @Override
    public String getMainMessage() {
        return "The Number is between " + game.getSmallest() +
                " and " + game.getBiggest() + ". Can you guess it?";
    }

    @Override
    public String getResultMessage() {

        if(game.isGameWon()){
            return "You guessed it. The number was " + game.getNumber();
        }else if(game.isGameLost()){
            return "You lost. The number was " + game.getNumber();
        }else if(!game.isValidNumberRange()){
            return "Invalid Number : " + game.getGuess();
        }else if(game.getRemainingGuesses() == game.getGuessCount()){
            return "What is your first guess?";
        }else{
            String direction = "Lower";
            if(game.getGuess() < game.getNumber()){
                direction = "Higher";
            }
            return direction + "! You have " + game.getRemainingGuesses() + " guess left.";
        }
    }
}
