package com.cbd.otpgateway.exceptions;

public class GenerateOtpException extends RuntimeException {
    public String logId;
    public String otpReqAttempt;
    public String errorCode;

    public GenerateOtpException(String message, String errorCode, String otpReqAttempt, String logId) {
        super(message);
        this.logId = logId;
        this.errorCode = errorCode;
        this.otpReqAttempt = otpReqAttempt;
    }
}
