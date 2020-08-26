package com.alibaba.chaosblade.api;

import com.alibaba.chaosblade.api.model.ExperimentSpec;
import com.alibaba.chaosblade.api.model.FlagSpec;
import com.alibaba.chaosblade.api.model.MatcherSpec;
import com.alibaba.chaosblade.api.service.ExperimentService;
import com.alibaba.chaosblade.recordmanager.entities.Experiment;
import com.alibaba.chaosblade.recordmanager.entities.Flag;
import com.alibaba.chaosblade.recordmanager.entities.Host;
import com.alibaba.chaosblade.recordmanager.entities.Matcher;
import com.alibaba.chaosblade.recordmanager.service.ExperimentRecordManager;
import com.alibaba.chaosblade.recordmanager.service.HostManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ExperimentServiceTests {

    @Autowired
    HostManager hostManager;

    @Autowired
    ExperimentService experimentService;

    @Autowired
    ExperimentRecordManager recordManager;

    @SpringBootApplication
    @EnableJpaAuditing
    static class TestConfiguration {
    }

    @Test
    void testCreateExperiment(){

        Host host = new Host();
        host.setHostIP("47.253.8.147");
        host.setHostPort(9526);
        host.setHostName("myHost");
        Host savedHost = hostManager.addHost(host);
        assertThat(savedHost).isNotNull();

        List<FlagSpec> flags = new ArrayList<>();
        flags.add(new FlagSpec("cpu-percent", "50"));

        List<MatcherSpec> matchers = new ArrayList<>();


        ExperimentSpec experimentSpec = new ExperimentSpec("cpu", "fullload", 1, "host", flags, matchers);

        Experiment experiment = experimentService.createExperiment(experimentSpec);

        assertThat(experiment).isNotNull();

    }

    @Test
    void testDestroyExperiment(){
        Flag flag1 = new Flag("offset", "100");
        Flag flag2 = new Flag("time", "200");
        Matcher matcher1 = new Matcher("local-port", "80");
        Matcher matcher2 = new Matcher("interface", "eth0");

        List<Flag> flags = new ArrayList<>();
        flags.add(flag1);
        flags.add(flag2);

        List<Matcher> matchers = new ArrayList<>();
        matchers.add(matcher1);
        matchers.add(matcher2);

        Host host = new Host();
        host.setHostIP("123.12.3.3");
        host.setHostPort(8080);
        host.setHostName("myHost");


        Experiment experiment = new Experiment();
        experiment.setUid("2345");
        experiment.setHost(host);
        experiment.setAction("delay");
        experiment.setTarget("network");
        experiment.setDateCreated(new Date());
        experiment.setFlags(flags);
        experiment.setMatchers(matchers);
        experiment.setStatus("success");

        Experiment savedExperiment = recordManager.saveExperimentRecord(experiment);

        Experiment destroyedExperiment = experimentService.destroyExperiment(savedExperiment);

        assertThat(destroyedExperiment).isNotNull();

        assertThat(destroyedExperiment.isDestroyed()).isTrue();

    }


}
