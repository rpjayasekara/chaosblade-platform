package com.alibaba.chaosblade.sdk.parser;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author randika
 */

@Service
public class ModelParser {

    Logger logger = LoggerFactory.getLogger(ModelParser.class);

    public List<ModelSpec> getAllModels(){
        
        Gson gson = new Gson();
        Yaml yaml = new Yaml();
        ArrayList<ModelSpec> modelSpecList = new ArrayList<>();

        try {
            InputStream inputStream = this.getClass()
                    .getClassLoader()
                    .getResourceAsStream("chaosblade.spec.yml");
            Map<String, Object> obj = yaml.load(inputStream);
            List<Map<String, Object>>  items = (List<Map<String, Object>>) (List<?>) obj.get("items");
            for(Map<String, Object> item: items){
                JsonElement jsonElement = gson.toJsonTree(item);
                ModelSpec pojo = gson.fromJson(jsonElement, ModelSpec.class);
                modelSpecList.add(pojo);
            }

        }catch (YAMLException e){
            logger.error("Problem in reading the file");
        }
        return modelSpecList;
    }

    public ModelSpec getModelByTargetName(String target){

        Gson gson = new Gson();
        Yaml yaml = new Yaml();
        ModelSpec pojo = null;

        try {
            InputStream inputStream = this.getClass()
                    .getClassLoader()
                    .getResourceAsStream("chaosblade.spec.yml");
            Map<String, Object> obj = yaml.load(inputStream);
            List<Map<String, Object>>  items = (List<Map<String, Object>>) (List<?>) obj.get("items");
            for(Map<String, Object> item: items){
                if(item.get("target").equals(target)){
                    JsonElement jsonElement = gson.toJsonTree(item);
                    pojo = gson.fromJson(jsonElement, ModelSpec.class);
                    break;
                }
            }
            if(pojo==null){
                logger.error("No such target name exists");
            }

        }catch (YAMLException e){
            logger.error("Problem in reading the file");
        }
        return pojo;
    }

}
