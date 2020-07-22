package com.alibaba.chaosblade.sdk.commandbuilder;

/**
 * @author randika
 */
public class StatusInputModel {

    private String uid;

    public StatusInputModel(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}
