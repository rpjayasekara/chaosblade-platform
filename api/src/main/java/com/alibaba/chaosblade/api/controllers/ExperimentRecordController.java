package com.alibaba.chaosblade.api.controllers;

import com.alibaba.chaosblade.recordmanager.entities.Experiment;
import com.alibaba.chaosblade.recordmanager.service.ExperimentRecordManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author randika
 */
@RestController
@RequestMapping("chaosblade/")
@CrossOrigin(origins = "http://localhost:4200")
public class ExperimentRecordController {

    @Autowired
    ExperimentRecordManager experimentRecordManager;

    @RequestMapping(value = "records", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Experiment> getAllExperiments(){

        return experimentRecordManager.getAllExperimentRecords();

    }

}
