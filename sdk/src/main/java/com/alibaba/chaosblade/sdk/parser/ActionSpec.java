package com.alibaba.chaosblade.sdk.parser;


import java.util.List;

/**
 * @author randika
 */
public class ActionSpec {

    private String action;

    private List<String> aliases;

    private String shortDesc;

    private String longDesc;

    private List<MatcherSpec> matchers;

    private List<FlagSpec> flags;

    public ActionSpec(String action, List<String> aliases, String shortDesc, String longDesc, List<MatcherSpec> matchers, List<FlagSpec> flags) {
        this.action = action;
        this.aliases = aliases;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.matchers = matchers;
        this.flags = flags;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public List<MatcherSpec> getMatchers() {
        return matchers;
    }

    public void setMatchers(List<MatcherSpec> matchers) {
        this.matchers = matchers;
    }

    public List<FlagSpec> getFlags() {
        return flags;
    }

    public void setFlags(List<FlagSpec> flags) {
        this.flags = flags;
    }
}
