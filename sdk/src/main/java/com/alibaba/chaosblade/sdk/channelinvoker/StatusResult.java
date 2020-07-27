package com.alibaba.chaosblade.sdk.channelinvoker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author randika
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusResult {

    private String Uid;

    private String Command;

    private String SubCommand;

    private String Flag;

    private String Status;

    private String Error;

    private String CreateTime;

    private String UpdateTime;

    public StatusResult(){
        // to create the pojo
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getCommand() {
        return Command;
    }

    public void setCommand(String command) {
        Command = command;
    }

    public String getSubCommand() {
        return SubCommand;
    }

    public void setSubCommand(String subCommand) {
        SubCommand = subCommand;
    }

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String flag) {
        Flag = flag;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(String updateTime) {
        UpdateTime = updateTime;
    }
}
