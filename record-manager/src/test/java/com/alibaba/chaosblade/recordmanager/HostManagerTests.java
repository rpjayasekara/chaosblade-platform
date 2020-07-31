package com.alibaba.chaosblade.recordmanager;

import com.alibaba.chaosblade.recordmanager.entities.Host;
import com.alibaba.chaosblade.recordmanager.service.HostManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HostManagerTests {

    @Autowired
    HostManager hostManager;

    @SpringBootApplication
    @EnableJpaAuditing
    static class TestConfiguration {
    }

    @Test
    void testAddHost(){

        Host host = new Host();
        host.setHostIP("192.168.1.1");
        host.setHostPort(8080);
        host.setHostName("myHost");
        Host savedHost = hostManager.addHost(host);

        assertThat(savedHost).isNotNull();
        assertThat(savedHost.getHostName()).isEqualTo("myHost");

    }

    @Test
    void testRemoveHost(){

        Host host = new Host();
        host.setHostIP("192.168.1.1");
        host.setHostPort(8080);
        host.setHostName("myHost");
        Host savedHost = hostManager.addHost(host);

        int noOfHosts = hostManager.getAllHosts().size();

        hostManager.removeHost(1);

        assertThat(noOfHosts-1).isEqualTo(2);

    }

    @Test
    void testGetAllHosts(){

        Host host = new Host();
        host.setHostIP("192.168.1.1");
        host.setHostPort(8080);
        host.setHostName("myHost");
        hostManager.addHost(host);
        hostManager.addHost(host);

        List<Host> hostList = hostManager.getAllHosts();

        assertThat(hostList).isNotNull();

    }

    @Test
    void testGetHostByHostID(){

        Host host = new Host();
        host.setHostIP("192.168.1.1");
        host.setHostPort(8080);
        host.setHostName("myHost");
        Host savedHost = hostManager.addHost(host);

        assertThat(hostManager.getHostById(savedHost.getHostID()).getHostName()).isEqualTo(savedHost.getHostName());

    }

}
