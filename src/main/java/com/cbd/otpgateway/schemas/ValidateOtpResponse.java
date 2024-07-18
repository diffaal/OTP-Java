package com.cbd.otpgateway.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidateOtpResponse {
    @JsonProperty("otp_validate_failed_attempt")
    public String otpValFailedAttempt;

    @JsonProperty("status")
    public String status;

    @JsonProperty("error_code")
    public String errorCode;

    @JsonProperty("error_message")
    public String errorMessage;

    public ValidateOtpResponse(String otpValFailedAttempt, String status, String errorCode, String errorMessage) {
        this.otpValFailedAttempt = otpValFailedAttempt;
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getOtpValFailedAttempt() {
        return otpValFailedAttempt;
    }

    public void setOtpValFailedAttempt(String otpValFailedAttempt) {
        this.otpValFailedAttempt = otpValFailedAttempt;
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
