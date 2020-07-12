package learning.programming.console;

import learning.programming.config.AppConfig;
import learning.programming.MessageGenerator;
import learning.programming.NumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Game Guess the Number");

        // Create Context (Container)
        ConfigurableApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);

        // Create numberGenerator Bean
        NumberGenerator numberGenerator
                = context.getBean(NumberGenerator.class);

        // Generating Random Number
        int num = numberGenerator.next();

        // Logging
        log.info("The Number Generated is {}", num);

        // Create game Bean form context
        MessageGenerator messageGenerator = context.getBean(MessageGenerator.class);

        log.info("getMainMessage = {}", messageGenerator.getMainMessage());
        log.info("getResultMessage = {}", messageGenerator.getResultMessage());


        // Close Context (Container)
        context.close();
    }
}
