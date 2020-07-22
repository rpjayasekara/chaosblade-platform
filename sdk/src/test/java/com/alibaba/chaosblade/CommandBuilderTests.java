package com.alibaba.chaosblade;

import com.alibaba.chaosblade.sdk.commandbuilder.CommandBuilder;
import com.alibaba.chaosblade.sdk.commandbuilder.CreateInputModel;
import com.alibaba.chaosblade.sdk.commandbuilder.StatusInputModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CommandBuilderTests {

    @Autowired
    private CommandBuilder commandBuilder;

    private Map<String, String> matchers;

    private Map<String, String> flags;

    private CreateInputModel createInputModel;

    private StatusInputModel statusInputModel;


    public CommandBuilderTests(){

        matchers = new HashMap<String, String>();
        matchers.put("local-port", "80");
        matchers.put("interface", "eth0");

        flags = new HashMap<String, String>();
        flags.put("time", "200");
        flags.put("offset", "100");

        createInputModel = new CreateInputModel("network", "delay", matchers, flags);

        statusInputModel = new StatusInputModel("4c6b4a3fc313e1d4");

    }


    @Test
    void testGetCreateCommand() {

        assertThat(commandBuilder.getCreateCommand(createInputModel)).isEqualTo("create network delay --local-port 80 --interface eth0 --offset 100 --time 200");


    }

    @Test
    void testGetStatusByUIDCommand(){

        assertThat(commandBuilder.getStatusByUIDCommand(statusInputModel)).isEqualTo("blade status 4c6b4a3fc313e1d4");
    }

    @SpringBootApplication
    static class TestConfiguration {
    }


}
