package com.alibaba.chaosblade.recordmanager.service;

import com.alibaba.chaosblade.recordmanager.dao.ExperimentDAO;
import com.alibaba.chaosblade.recordmanager.entities.Experiment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


/**
 * @author randika
 */
@Service
public class ExperimentRecordManager {

    @Autowired
    ExperimentDAO experimentDAO;

    @Transactional
    public Experiment saveExperimentRecord(Experiment experiment){

        return experimentDAO.save(experiment);

    }

    @Transactional
    public void removeExperimentRecord(String uid){

        experimentDAO.deleteById(uid);

    }

    public List<Experiment> getAllExperimentRecords(){

        return experimentDAO.findAll();

    }

    public Experiment getExperimentByUID(String uid){


        Optional<Experiment> experiment = experimentDAO.findById(uid);

        if(experiment.isPresent()){
            return experiment.get();
        } else {
            return null;
        }

    }


}
