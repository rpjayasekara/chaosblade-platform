package com.alibaba.chaosblade.api;

import com.alibaba.chaosblade.api.model.ExperimentSpec;
import com.alibaba.chaosblade.api.model.FlagSpec;
import com.alibaba.chaosblade.api.model.MatcherSpec;
import com.alibaba.chaosblade.api.service.ExperimentService;
import com.alibaba.chaosblade.recordmanager.entities.Experiment;
import com.alibaba.chaosblade.recordmanager.entities.Host;
import com.alibaba.chaosblade.recordmanager.service.HostManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ExperimentServiceTests {

    @Autowired
    HostManager hostManager;

    @Autowired
    ExperimentService experimentService;

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


}
