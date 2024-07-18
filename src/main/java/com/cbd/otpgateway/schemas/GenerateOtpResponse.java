package com.cbd.otpgateway.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenerateOtpResponse {
    @JsonProperty("otp_req_attempt")
    public String otpReqAttempt;

    @JsonProperty("status")
    public String status;

    @JsonProperty("error_code")
    public String errorCode;

    @JsonProperty("error_message")
    public String errorMessage;

    public GenerateOtpResponse() {}

    public GenerateOtpResponse(String otpReqAttempt, String status, String errorCode, String errorMessage) {
        this.otpReqAttempt = otpReqAttempt;
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getOtpReqAttempt() {
        return otpReqAttempt;
    }

    public void setOtpReqAttempt(String otpReqAttempt) {
        this.otpReqAttempt = otpReqAttempt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
