package com.dish.springplayground.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.Arrays.asList;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {
        "wordCount.caseSensitive=true",
        "wordCount.words.skip[0]=one",
        "wordCount.words.skip[1]=fish",
        "wordCount.words.skip[2]=two",
        "wordCount.words.skip[3]=fish",
})
public class WordCounterConfigTest {

    @Autowired
    private WordCounterConfig config;

    @Test
    public void testConfigSettings() {
        assertThat(config.isCaseSensitive(), equalTo(true));
        assertThat(config.getWords().getSkip(), equalTo(asList("one", "fish", "two", "fish")));
    }
}