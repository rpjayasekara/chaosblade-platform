package com.alibaba.chaosblade.sdk.channelinvoker;

import com.alibaba.chaosblade.sdk.parser.ModelParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author randika
 */
@Service
public class ChannelInvoker {

    @Autowired
    private RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(ChannelInvoker.class);


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }


    public StatusResponse getExperimentStatus(String hostAddress, String command){

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON));
        restTemplate.getMessageConverters().add(0, converter);

        String url = "http://"+hostAddress+"/chaosblade?cmd="+command;

        try {

            StatusResponse cr = restTemplate.getForObject(url, StatusResponse.class);
            return cr;

        }catch (ResourceAccessException e){

            logger.error("Problem connecting to the host");
            return null;

        }
    }

    public CreateResponse createExperiment(String hostAddress, String command){

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON));
        restTemplate.getMessageConverters().add(0, converter);

        String url = "http://"+hostAddress+"/chaosblade?cmd="+command;

        try {

            CreateResponse cr = restTemplate.getForObject(url, CreateResponse.class);
            return cr;

        }catch (ResourceAccessException e){

            logger.error("Problem connecting to the host");
            return null;

        }

    }

    public DestroyResponse destroyExperiment(String hostAddress, String command){

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON));
        restTemplate.getMessageConverters().add(0, converter);

        String url = "http://"+hostAddress+"/chaosblade?cmd="+command;

        logger.info(command);

        try {

            logger.info("hit!");
            DestroyResponse cr = restTemplate.getForObject(url, DestroyResponse.class);
            logger.info("experiment destroyed");
            return cr;

        }catch (ResourceAccessException e){

            logger.error("Problem connecting to the host");
            return null;

        }
    }

}
