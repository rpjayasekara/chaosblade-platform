package com.alibaba.chaosblade.sdk;
import com.alibaba.chaosblade.sdk.parser.ModelParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ParserTests {
    @Autowired
    private ModelParser modelParser;

    @Test
    void contextLoads() {
        assertThat(modelParser.parseYMLtoObject()).isNotNull();
    }

    @SpringBootApplication
    static class TestConfiguration {
    }
}
