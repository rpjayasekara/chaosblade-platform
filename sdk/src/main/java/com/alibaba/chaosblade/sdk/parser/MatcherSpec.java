package com.alibaba.chaosblade.sdk.parser;

/**
 * @author randika
 */
public class MatcherSpec {

    private String name;

    private String desc;

    private boolean noArgs;

    private boolean required;

    public MatcherSpec(String name, String desc, boolean noArgs, boolean required){
        this.name=name;
        this.desc=desc;
        this.noArgs=noArgs;
        this.required=required;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isNoArgs() {
        return noArgs;
    }

    public void setNoArgs(boolean noArgs) {
        this.noArgs = noArgs;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
