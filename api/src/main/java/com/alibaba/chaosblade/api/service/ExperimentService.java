package com.alibaba.chaosblade.api.service;

import com.alibaba.chaosblade.api.exceptions.ErrorInExperimentCreationException;
import com.alibaba.chaosblade.api.model.ExperimentSpec;
import com.alibaba.chaosblade.api.model.FlagSpec;
import com.alibaba.chaosblade.api.model.MatcherSpec;
import com.alibaba.chaosblade.recordmanager.entities.Experiment;
import com.alibaba.chaosblade.recordmanager.entities.Flag;
import com.alibaba.chaosblade.recordmanager.entities.Host;
import com.alibaba.chaosblade.recordmanager.entities.Matcher;
import com.alibaba.chaosblade.recordmanager.service.ExperimentRecordManager;
import com.alibaba.chaosblade.recordmanager.service.HostManager;
import com.alibaba.chaosblade.sdk.channelinvoker.ChannelInvoker;
import com.alibaba.chaosblade.sdk.channelinvoker.CreateResponse;
import com.alibaba.chaosblade.sdk.commandbuilder.CommandBuilder;
import com.alibaba.chaosblade.sdk.commandbuilder.CreateInputModel;
import com.alibaba.chaosblade.sdk.parser.ModelParser;
import com.alibaba.chaosblade.sdk.parser.ModelSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * @author randika
 */
@Service
public class ExperimentService {

    @Autowired
    ModelParser modelParser;

    @Autowired
    ExperimentRecordManager experimentRecordManager;

    @Autowired
    HostManager hostManager;

    @Autowired
    CommandBuilder commandBuilder;

    @Autowired
    ChannelInvoker channelInvoker;

    public Experiment createExperiment(ExperimentSpec experimentSpec){

        HashMap<String, String> flags = new HashMap<>();
        HashMap<String, String> matchers = new HashMap<>();

        for(FlagSpec flag: experimentSpec.getFlags()){
            flags.put(flag.getFlagName(), flag.getFlagValue());
        }

        for(MatcherSpec matcher: experimentSpec.getMatchers()){
            matchers.put(matcher.getMatcherName(), matcher.getMatcherValue());
        }

        CreateInputModel createInputModel = new CreateInputModel(experimentSpec.getTarget(), experimentSpec.getAction(), matchers, flags);

        String command = commandBuilder.getCreateCommand(createInputModel);

        Host host = hostManager.getHostById(experimentSpec.getHostID());

        String url = host.getHostIP()+":"+host.getHostPort();

        CreateResponse createResponse = channelInvoker.createExperiment(url, command);

        if (createResponse.isSuccess()){

            List<Flag> flagsDB = new ArrayList<>();
            List<Matcher> mathcersDB = new ArrayList<>();
            for(FlagSpec flag: experimentSpec.getFlags()){
                flagsDB.add(new Flag(flag.getFlagName(), flag.getFlagValue()));
            }
            for(MatcherSpec matcher: experimentSpec.getMatchers()){
                mathcersDB.add(new Matcher(matcher.getMatcherName(), matcher.getMatcherValue()));
            }
            Experiment experiment = new Experiment();
            experiment.setUid(createResponse.getResult());
            experiment.setHost(host);
            experiment.setAction(experimentSpec.getAction());
            experiment.setTarget(experimentSpec.getTarget());
            experiment.setDateCreated(new Date());
            experiment.setFlags(flagsDB);
            experiment.setMatchers(mathcersDB);
            experiment.setDestroyed(false);
            experiment.setStatus("success");

            experiment = experimentRecordManager.saveExperimentRecord(experiment);

            return experiment;

        } else {

            throw  new ErrorInExperimentCreationException();

        }

    }

    public List<ModelSpec> getAllExperimentModels(){

        return modelParser.getAllModels();

    }

    public Experiment destroyExperiment(Experiment experiment){

        String cmd = commandBuilder.getDestroyCommand(experiment.getUid());
        Host host = experiment.getHost();
        String url = host.getHostIP()+":"+host.getHostPort();
        channelInvoker.destroyExperiment( url, cmd);
        experiment.setDestroyed(true);
        return experimentRecordManager.saveExperimentRecord(experiment);

    }


}
