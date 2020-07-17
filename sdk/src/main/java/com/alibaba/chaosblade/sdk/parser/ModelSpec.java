package com.alibaba.chaosblade.sdk.parser;

import java.util.List;

public class ModelSpec {

    private String target;

    private String shortDesc;

    private String longDesc;

    private String example;

    private List<ActionSpec> actions;

    public ModelSpec(String target, String shortDesc, String longDesc, String example, List<ActionSpec> actions) {
        this.target = target;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.example = example;
        this.actions = actions;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
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

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public List<ActionSpec> getActions() {
        return actions;
    }

    public void setActions(List<ActionSpec> actions) {
        this.actions = actions;
    }
}
