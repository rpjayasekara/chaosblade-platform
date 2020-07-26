package com.alibaba.chaosblade.sdk.channelinvoker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author randika
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateResponse {

    private int code;

    private boolean success;

    private String result;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
