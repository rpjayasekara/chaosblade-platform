package com.alibaba.chaosblade.sdk.channelinvoker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author randika
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusResponse {

    private int code;

    private boolean success;

    private StatusResult result;

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

    public StatusResult getResult() {
        return result;
    }

    public void setResult(StatusResult result) {
        this.result = result;
    }
}
