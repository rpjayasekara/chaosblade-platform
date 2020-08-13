package com.alibaba.chaosblade.api.model;

/**
 * @author randika
 */
public class MatcherSpec {

    private String matcherName;

    private String matcherValue;

    public MatcherSpec(String matcherName, String matcherValue) {
        this.matcherName = matcherName;
        this.matcherValue = matcherValue;
    }

    public String getMatcherName() {
        return matcherName;
    }

    public void setMatcherName(String matcherName) {
        this.matcherName = matcherName;
    }

    public String getMatcherValue() {
        return matcherValue;
    }

    public void setMatcherValue(String matcherValue) {
        this.matcherValue = matcherValue;
    }

}
