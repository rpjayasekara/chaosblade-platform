package com.alibaba.chaosblade.api.model;

import java.util.List;

/**
 * @author randika
 */
public class ExperimentSpec {

    private String target;

    private String action;

    private int hostID;

    private String scope;

    private List<FlagSpec> flags;

    private List<MatcherSpec> matchers;

    public ExperimentSpec(String target, String action, int hostID, String scope, List<FlagSpec> flags, List<MatcherSpec> matchers) {
        this.target = target;
        this.action = action;
        this.hostID = hostID;
        this.scope = scope;
        this.flags = flags;
        this.matchers = matchers;
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

    public int getHostID() {
        return hostID;
    }

    public void setHostID(int hostID) {
        this.hostID = hostID;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<FlagSpec> getFlags() {
        return flags;
    }

    public void setFlags(List<FlagSpec> flags) {
        this.flags = flags;
    }

    public List<MatcherSpec> getMatchers() {
        return matchers;
    }

    public void setMatchers(List<MatcherSpec> matchers) {
        this.matchers = matchers;
    }

}
