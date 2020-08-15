package com.alibaba.chaosblade.api.controllers;

import com.alibaba.chaosblade.api.model.ExperimentSpec;
import com.alibaba.chaosblade.api.service.ExperimentService;
import com.alibaba.chaosblade.recordmanager.entities.Experiment;
import com.alibaba.chaosblade.recordmanager.service.HostManager;
import com.alibaba.chaosblade.sdk.parser.ModelSpec;
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
public class ExperimentController {

    @Autowired
    ExperimentService experimentService;

    @Autowired
    HostManager hostManager;

    @RequestMapping(value = "experiment", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<ModelSpec> getAllExperiments(){

        return experimentService.getAllExperimentModels();

    }

    @RequestMapping(value = "experiment", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Experiment createExperiment(@RequestBody ExperimentSpec experimentSpec){

        return experimentService.createExperiment(experimentSpec);

    }

    @RequestMapping(value = "experimentdelete", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Experiment destroyExperiment(@RequestBody Experiment experiment){

        return experimentService.destroyExperiment(experiment);

    }

}
