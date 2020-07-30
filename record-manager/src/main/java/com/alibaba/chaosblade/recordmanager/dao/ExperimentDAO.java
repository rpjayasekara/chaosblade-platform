package com.alibaba.chaosblade.recordmanager.dao;



import com.alibaba.chaosblade.recordmanager.entities.Experiment;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author randika
 */
public interface ExperimentDAO extends JpaRepository<Experiment, String> {

}