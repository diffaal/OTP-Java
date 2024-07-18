package com.cbd.otpgateway.services;

import com.cbd.otpgateway.configs.AppConfig;
import com.cbd.otpgateway.externals.WhatsappGateway;
import com.cbd.otpgateway.helpers.CountryCodeHelper;
import com.cbd.otpgateway.repositories.OtpListRepository;
import com.cbd.otpgateway.schemas.GenerateOtpRequest;
import com.cbd.otpgateway.schemas.GenerateOtpResponse;
import com.cbd.otpgateway.schemas.WhatsappGatewayRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbd.otpgateway.repositories.OtpActivityRepository;

import java.util.Random;

@Service
public class GenerateOtpService {
    private final AppConfig appConfig;
    private final OtpActivityRepository otpActivityRepository;
    private final OtpListRepository otpListRepository;
    private final CountryCodeHelper countryCodeHelper;
    private final WhatsappGateway whatsappGateway;
    private static final Logger logger = LoggerFactory.getLogger(GenerateOtpService.class);

    @Autowired
    public GenerateOtpService(
            OtpActivityRepository otpActivityRepository,
            OtpListRepository otpListRepository,
            AppConfig appConfig,
            CountryCodeHelper countryCodeHelper,
            WhatsappGateway whatsappGateway
    ) {
        this.appConfig = appConfig;
        this.otpActivityRepository = otpActivityRepository;
        this.otpListRepository = otpListRepository;
        this.countryCodeHelper = countryCodeHelper;
        this.whatsappGateway = whatsappGateway;
    }

    public GenerateOtpResponse generateOtp(GenerateOtpRequest req, String logId) {
        GenerateOtpResponse response;
        response = new GenerateOtpResponse();
        String otpCode = "";

        countryCodeHelper.validatePhoneNumber(req.getPhoneNumber(), req.getCountryCode(), logId);

        Random random = new Random();
        for (int i = 0; i < appConfig.getOtpLength(); i++) {
            otpCode += String.valueOf(random.nextInt(10));
        }

        WhatsappGatewayRequest whatsappGatewayRequest = new WhatsappGatewayRequest(
                "XPORA",
                appConfig.getWaProjectId(),
                appConfig.getWaType(),
                req.getPhoneNumber(),
                otpCode,
                "",
                "",
                req.getProductCode()
        );

        whatsappGateway.sendMessage(whatsappGatewayRequest, logId);

        response.setStatus("SUCCESS");
        response.setOtpReqAttempt("1");
        response.setErrorCode("");
        response.setErrorMessage("");
        return response;
    }
    
}
