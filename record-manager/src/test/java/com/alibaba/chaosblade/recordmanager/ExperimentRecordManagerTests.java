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
public class ExperimentRecordManagerTests {

    @Autowired
    ExperimentRecordManager experimentRecordManager;

    public ExperimentRecordManagerTests(){

    }

    @SpringBootApplication
    @EnableJpaAuditing
    static class TestConfiguration {
    }

    @Test
    void contextLoads() {

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

        assertThat(experimentRecordManager.saveExperimentRecord(experiment)).isNotNull();

    }

    @Test
    void testSaveExperimentRecord(){

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

        Experiment savedExperiment = experimentRecordManager.saveExperimentRecord(experiment);

        assertThat(savedExperiment).isNotNull();
        assertThat(savedExperiment.getTarget()).isEqualTo("network");
        assertThat(savedExperiment.getAction()).isEqualTo("delay");

    }

    @Test
    void testRemoveExperimentRecord(){

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
        experiment.setUid("5634");
        experiment.setHost(host);
        experiment.setAction("delay");
        experiment.setTarget("network");
        experiment.setDateCreated(new Date());
        experiment.setFlags(flags);
        experiment.setMatchers(matchers);
        experiment.setStatus("success");

        Experiment savedExperiment = experimentRecordManager.saveExperimentRecord(experiment);

        int noOfRecords = experimentRecordManager.getAllExperimentRecords().size();

        experimentRecordManager.removeExperimentRecord(savedExperiment.getUid());

        assertThat(noOfRecords-1).isEqualTo(1);

    }

    @Test
    void testGetAllExperiments(){

        List<Experiment> experiments = experimentRecordManager.getAllExperimentRecords();
        assertThat(experiments).isNotNull();

    }

    @Test
    void testGetExperimentByUID(){
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
        experiment.setUid("7897");
        experiment.setHost(host);
        experiment.setAction("delay");
        experiment.setTarget("network");
        experiment.setDateCreated(new Date());
        experiment.setFlags(flags);
        experiment.setMatchers(matchers);
        experiment.setStatus("success");

        Experiment savedExperiment = experimentRecordManager.saveExperimentRecord(experiment);

        Experiment retrievedExperiment = experimentRecordManager.getExperimentByUID(savedExperiment.getUid());

        assertThat(retrievedExperiment).isNotNull();
        assertThat(retrievedExperiment.getTarget()).isEqualTo("network");
        assertThat(retrievedExperiment.getAction()).isEqualTo("delay");
        assertThat(experimentRecordManager.getExperimentByUID("12")).isNull();

    }

}
