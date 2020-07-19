package com.alibaba.chaosblade.sdk;
import com.alibaba.chaosblade.sdk.parser.*;
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

        assertThat(modelParser.getAllModels()).isNotNull();

    }

    @Test
    void checkModelSpec(){

        ModelSpec modelSpec = modelParser.getAllModels().get(0);

        assertThat(modelSpec.getTarget()).isEqualTo("cpu");
        assertThat(modelSpec.getLongDesc()).isEqualTo("Cpu experiment, for example full load");
        assertThat(modelSpec.getShortDesc()).isEqualTo("Cpu experiment");
        assertThat(modelSpec.getExample()).isEqualTo("cpu fullload");
        assertThat(modelSpec.getActions()).isNotNull();
        assertThat(modelSpec.getScope()).isEqualTo("host");

    }

    @Test
    void checkActionSpec(){

        ActionSpec actionSpec = modelParser.getAllModels().get(0).getActions().get(0);

        assertThat(actionSpec.getAction()).isEqualTo("fullload");
        assertThat(actionSpec.getAliases().get(0)).isEqualTo("fl");
        assertThat(actionSpec.getLongDesc()).isEqualTo("cpu load");
        assertThat(actionSpec.getShortDesc()).isEqualTo("cpu load");
        assertThat(actionSpec.getFlags()).isNotNull();
        assertThat(actionSpec.getMatchers()).isNull();

    }

    @Test
    void checkMatcherSpec(){

        MatcherSpec matcherSpec = modelParser.getAllModels().get(2).getActions().get(0).getMatchers().get(0);

        assertThat(matcherSpec.getName()).isEqualTo("process");
        assertThat(matcherSpec.getDesc()).isEqualTo("Process name");
        assertThat(matcherSpec.isNoArgs()).isFalse();
        assertThat(matcherSpec.isRequired()).isFalse();

    }

    @Test
    void checkFlagSpec(){

        FlagSpec flagSpec = modelParser.getAllModels().get(2).getActions().get(0).getFlags().get(0);

        assertThat(flagSpec.getName()).isEqualTo("timeout");
        assertThat(flagSpec.getDesc()).isEqualTo("set timeout for experiment");
        assertThat(flagSpec.isNoArgs()).isFalse();
        assertThat(flagSpec.isRequired()).isFalse();

    }

    @Test
    void checkGetObjectByTarget(){

        ModelSpec modelSpec = modelParser.getObjectByTargetName("cpu");
        assertThat(modelSpec).isNotNull();

        modelSpec = modelParser.getObjectByTargetName("Cpu");
        assertThat(modelSpec).isNull();

    }

    @SpringBootApplication
    static class TestConfiguration {
    }
}
