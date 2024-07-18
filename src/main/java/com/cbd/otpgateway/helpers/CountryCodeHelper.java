package com.cbd.otpgateway.helpers;

import com.cbd.otpgateway.exceptions.GenerateOtpException;
import com.cbd.otpgateway.models.CountryCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class CountryCodeHelper {
    private List<CountryCode> countryCodes;

    @Value("${country.code.filepath}") // Path to the JSON file configured in application.properties
    private String jsonFilePath;

    @PostConstruct
    public void init() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            countryCodes = objectMapper.readValue(new File(jsonFilePath), objectMapper.getTypeFactory().constructCollectionType(List.class, CountryCode.class));
        } catch (IOException e) {
            e.printStackTrace(); // Handle exception appropriately
        }
    }

    public void validatePhoneNumber(String phoneNumber, String countryCode, String logId) {
        int lenCountryCode = countryCode.length();
        if (!phoneNumber.substring(0, lenCountryCode).equals(countryCode)) {
            throw new GenerateOtpException(
                    "(MICROSERVICE) COUNTRY CODE NOT MATCH WITH PHONE NUMBER",
                    "9003",
                    "",
                    logId
            );
        }


        if (!phoneNumber.equals("62")) {
            boolean found = false;
            String countryCodeCheck = "+" + countryCode;
            for (CountryCode code : countryCodes) {
                if(code.getCode().equals(countryCodeCheck)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new GenerateOtpException(
                        "(MICROSERVICE) INVALID COUNTRY CODE",
                        "9002",
                        "",
                        logId
                );
            }
        }
    }
}
