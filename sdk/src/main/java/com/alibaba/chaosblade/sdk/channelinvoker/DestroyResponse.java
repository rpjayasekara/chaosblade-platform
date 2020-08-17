package com.alibaba.chaosblade.sdk.channelinvoker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author randika
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DestroyResponse {

    private int code;

    private boolean success;

    public DestroyResponse(){
//        to create the pojo
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
