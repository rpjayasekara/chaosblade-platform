package com.alibaba.chaosblade.sdk.commandbuilder;

import java.util.Map;

/**
 * @author randika
 */
public class CreateInputModel {

    private String target;
    private String action;
    private Map<String, String> matchers;
    private Map<String, String> flags;

    public CreateInputModel(String target, String action, Map<String, String> matchers, Map<String, String> flags) {
        this.target = target;
        this.action = action;
        this.matchers = matchers;
        this.flags = flags;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Map<String, String> getMatchers() {
        return matchers;
    }

    public void setMatchers(Map<String, String> matchers) {
        this.matchers = matchers;
    }

    public Map<String, String> getFlags() {
        return flags;
    }

    public void setFlags(Map<String, String> flags) {
        this.flags = flags;
    }

}
