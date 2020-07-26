package com.alibaba.chaosblade.sdk.channelinvoker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author randika
 */
@Service
public class ChannelInvoker {

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    public CreateResponse createExperiment(String hostAddress, String command){

        String url = hostAddress+command;
        return restTemplate.getForObject(url, CreateResponse.class);

    }

    public StatusResponse getExperimentStatus(String hostAddress, String command){

        String url = hostAddress+command;
        return restTemplate.getForObject(url, StatusResponse.class);

    }
}
