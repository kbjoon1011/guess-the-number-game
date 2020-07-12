package learning.programming.console;

import learning.programming.Game;
import learning.programming.MessageGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleGuessNumber {

    private static final Logger log = LoggerFactory.getLogger(ConsoleGuessNumber.class);

    @Autowired
    public Game game;

    @Autowired
    public MessageGenerator messageGenerator;

    @EventListener(ContextRefreshedEvent.class)
    public void start() {
        log.info("start() --> Container ready for use.");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(messageGenerator.getMainMessage());
            System.out.println(messageGenerator.getResultMessage());

            int guess = scanner.nextInt();
            scanner.nextLine();
            game.setGuess(guess);
            game.check();

            if (game.isGameWon() || game.isGameLost()) {
                System.out.println(messageGenerator.getResultMessage());
                System.out.println("Play Again? y/n");

                String playStringAgain = scanner.nextLine().trim();
                if (!playStringAgain.equalsIgnoreCase("y")) {
                    break;
                }
                game.reset();
            }

        }
    }
}
