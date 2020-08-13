package com.alibaba.chaosblade.api.model;

/**
 * @author randika
 */
public class FlagSpec {

    private String flagName;

    private String flagValue;

    public FlagSpec(String flagName, String flagValue) {
        this.flagName = flagName;
        this.flagValue = flagValue;
    }

    public String getFlagName() {
        return flagName;
    }

    public void setFlagName(String flagName) {
        this.flagName = flagName;
    }

    public String getFlagValue() {
        return flagValue;
    }

    public void setFlagValue(String flagValue) {
        this.flagValue = flagValue;
    }

}
