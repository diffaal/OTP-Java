package com.cbd.otpgateway.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class ValidateOtpRequest {
    @NotBlank(message = "channel is required")
    @JsonProperty("channel")
    private String channel;

    @NotBlank(message = "phone number is required")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @NotBlank(message = "country code is required")
    @JsonProperty("country_code")
    private String countryCode;

    @NotBlank(message = "otp code is required")
    @JsonProperty("otp_code")
    private String otpCode;

    @JsonProperty("product_code")
    private String productCode;

    public String getChannel() {
        return channel;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public String getProductCode() {
        return productCode;
    }
}
