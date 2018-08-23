package com.dish.springplayground.config;

import com.dish.springplayground.services.WordCounter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WordCounterConfig {

    @Bean
    public WordCounter getWordCounter() {
        return new WordCounter();
    }
}
