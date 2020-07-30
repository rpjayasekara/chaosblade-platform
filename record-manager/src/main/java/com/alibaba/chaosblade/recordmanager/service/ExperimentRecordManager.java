package com.alibaba.chaosblade.recordmanager.service;

import com.alibaba.chaosblade.recordmanager.dao.ExperimentDAO;
import com.alibaba.chaosblade.recordmanager.entities.Experiment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


/**
 * @author randika
 */
@Service
public class ExperimentRecordManager {

    @Autowired
    ExperimentDAO experimentDAO;

    @Transactional
    public Experiment saveExperiment(Experiment experiment){

        return experimentDAO.save(experiment);

    }
}
