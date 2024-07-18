package com.cbd.otpgateway.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${PARAM_OTP_LENGTH}")
    private int otpLength;
    @Value("${PARAM_OTP_GEN_LIMIT}")
    private int otpGenerateLimit;
    @Value("${PARAM_OTP_VAL_LIMIT}")
    private int otpValidateLimit;
    @Value("${PARAM_OTP_GEN_COOLDOWN_HOURS}")
    private int otpGenerateCooldownHours;
    @Value("${PARAM_OTP_VAL_COOLDOWN_HOURS}")
    private int otpValidateCooldownHours;
    @Value("${PARAM_OTP_CODE_EXPIRED_MINUTES}")
    private int otpCodeExpiredMinutes;
    @Value("${PARAM_WA_GATEWAY_URL}")
    private String waGatewayUrl;
    @Value("${PARAM_WA_PROJECT_ID}")
    private String waProjectId;
    @Value("${PARAM_WA_TYPE}")
    private String waType;
    @Value("${PARAM_REQUEST_TIMEOUT}")
    private int reqTimeout;

    public int getOtpLength() {
        return otpLength;
    }

    public int getOtpGenerateLimit() {
        return otpGenerateLimit;
    }

    public int getOtpValidateLimit() {
        return otpValidateLimit;
    }

    public int getOtpGenerateCooldownHours() {
        return otpGenerateCooldownHours;
    }

    public int getOtpValidateCooldownHours() {
        return otpValidateCooldownHours;
    }

    public int getOtpCodeExpiredMinutes() {
        return otpCodeExpiredMinutes;
    }

    public String getWaGatewayUrl() {
        return waGatewayUrl;
    }

    public String getWaProjectId() {
        return waProjectId;
    }

    public String getWaType() {
        return waType;
    }

    public int getReqTimeout() {
        return reqTimeout;
    }
}
