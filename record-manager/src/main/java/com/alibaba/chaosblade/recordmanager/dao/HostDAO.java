package com.alibaba.chaosblade.recordmanager.dao;

import com.alibaba.chaosblade.recordmanager.entities.Host;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author randika
 */
public interface HostDAO extends JpaRepository<Host, Integer> {

}
