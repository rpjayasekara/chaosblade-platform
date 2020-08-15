package com.alibaba.chaosblade.sdk.commandbuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author randika
 */
@Service
public class CommandBuilder {

    Logger logger = LoggerFactory.getLogger(CommandBuilder.class);

    public String getCreateCommand(CreateInputModel input){

        StringBuilder cmd = new StringBuilder("create");
        cmd.append(" "+input.getTarget());
        cmd.append(" "+input.getAction());
        for(String key: input.getMatchers().keySet()){
            cmd.append(" --"+key+" ");
            cmd.append(input.getMatchers().get(key));
        }
        for(String key: input.getFlags().keySet()){
            cmd.append(" --"+key+" ");
            cmd.append(input.getFlags().get(key));
        }
        logger.info("create command generated");
        return cmd.toString();

    }

    public String getStatusByUIDCommand(StatusInputModel input){

        StringBuilder cmd = new StringBuilder("blade status ");
        cmd.append(input.getUid());
        return cmd.toString();

    }

    public String getDestroyCommand(String uid){
        StringBuilder cmd = new StringBuilder("destroy ");
        cmd.append(uid);
        return cmd.toString();
    }

}
