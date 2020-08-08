package learning.programming.config;

import learning.programming.GussCount;
import learning.programming.MaxNumber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/game.properties")
public class GameConfig {
    private int maxNumber = 50;

    private int guessCount = 15;

    @Bean
    @MaxNumber
    public int maxNumber1() {
        return maxNumber;
    }

    @Bean
    @GussCount
    public int guessCount1(){
        return guessCount;
    }

}
