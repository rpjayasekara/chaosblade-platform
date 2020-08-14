package com.alibaba.chaosblade.api;

import com.alibaba.chaosblade.recordmanager.entities.Host;
import com.alibaba.chaosblade.recordmanager.service.HostManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * @author randika
 */
@SpringBootApplication(scanBasePackages = "com.alibaba.chaosblade")
@EntityScan("com.alibaba.chaosblade")
@EnableJpaRepositories("com.alibaba.chaosblade")
public class API {

	public static void main(String[] args) {

		SpringApplication.run(API.class, args);

	}

}
