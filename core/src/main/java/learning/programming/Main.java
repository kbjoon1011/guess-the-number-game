package learning.programming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private static final String CONFIG_LOCATION = "beans.xml";

    public static void main(String[] args) {
        log.info("Game Guess the Number");

        // Create Context (Container)
        ConfigurableApplicationContext context
                = new ClassPathXmlApplicationContext(CONFIG_LOCATION);

        // Create numberGenerator Bean
        NumberGenerator numberGenerator
                = context.getBean("numberGenerator", NumberGenerator.class);

        // Generating Random Number
        int num = numberGenerator.next();

        // Logging
        log.info("The Number Generated is {}", num);

        // Create game Bean
        Game game = context.getBean(GameImpl.class);

        // Reset
        game.reset();

        // Close Context (Container)
        context.close();
    }
}
