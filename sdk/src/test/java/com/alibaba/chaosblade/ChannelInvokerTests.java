package com.alibaba.chaosblade;

import com.alibaba.chaosblade.sdk.channelinvoker.ChannelInvoker;
import com.alibaba.chaosblade.sdk.channelinvoker.CreateResponse;
import com.alibaba.chaosblade.sdk.channelinvoker.DestroyResponse;
import com.alibaba.chaosblade.sdk.channelinvoker.StatusResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ChannelInvokerTests {

    @Autowired
    ChannelInvoker channelInvoker;

    @SpringBootApplication
    static class TestConfiguration {
    }

    @Test
    void testGetExperimentStatus(){

        StatusResponse statusResponse = channelInvoker.getExperimentStatus("47.253.8.147:9526", "status 1a19239f8af1f055");
        assertThat(statusResponse).isNotNull();
        assertThat(statusResponse.getCode()).isEqualTo(200);
        assertThat(statusResponse.isSuccess()).isTrue();
        assertThat(statusResponse.getResult()).isNotNull();

    }

    @Test
    void testCreateExperiment(){

        CreateResponse createResponse = channelInvoker.createExperiment("47.253.8.147:9526", "create cpu load --cpu-percent 50");
        assertThat(createResponse).isNotNull();
        assertThat(createResponse.getCode()).isEqualTo(200);
        assertThat(createResponse.isSuccess()).isTrue();
        assertThat(createResponse.getResult()).isNotNull();

    }

    @Test
    void testDestroyExperiment(){

        DestroyResponse destroyResponse = channelInvoker.destroyExperiment( "url","destroy f96a78fb43ae7623");
        assertThat(destroyResponse).isNotNull();
        assertThat(destroyResponse.getCode()).isEqualTo(200);
        assertThat(destroyResponse.isSuccess()).isTrue();
        assertThat(destroyResponse.getResult()).isNotNull();

    }

}
