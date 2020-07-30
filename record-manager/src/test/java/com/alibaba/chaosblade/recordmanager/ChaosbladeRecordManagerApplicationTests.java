package com.alibaba.chaosblade.recordmanager;

import com.alibaba.chaosblade.recordmanager.entities.Experiment;
import com.alibaba.chaosblade.recordmanager.entities.Flag;
import com.alibaba.chaosblade.recordmanager.entities.Host;
import com.alibaba.chaosblade.recordmanager.entities.Matcher;
import com.alibaba.chaosblade.recordmanager.service.ExperimentRecordManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ChaosbladeRecordManagerApplicationTests {

	@Autowired
	ExperimentRecordManager experimentRecordManager;

	@SpringBootApplication
	@EnableJpaAuditing
	static class TestConfiguration {
	}

	@Test
	void contextLoads() {

		Flag flag = new Flag("sleep", "200");
		Matcher matcher = new Matcher("abc", "100");

		List<Flag> flags = new ArrayList<>();
		flags.add(flag);

		List<Matcher> matchers = new ArrayList<>();
		matchers.add(matcher);

		Host host = new Host();
		host.setHostIP("123.12.3.3");
		host.setHostPort(8080);
		host.setHostName("myHost");


		Experiment experiment = new Experiment();
		experiment.setUid("2345");
		experiment.setHost(host);
		experiment.setAction("full load");
		experiment.setTarget("cpu");
		experiment.setDateCreated(new Date());
		experiment.setFlags(flags);
		experiment.setMatchers(matchers);

		assertThat(experimentRecordManager.saveExperiment(experiment)).isNotNull();

	}

}
