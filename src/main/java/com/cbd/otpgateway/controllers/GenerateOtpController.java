package com.cbd.otpgateway.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbd.otpgateway.exceptions.GenerateOtpException;
import com.cbd.otpgateway.schemas.GenerateOtpRequest;
import com.cbd.otpgateway.schemas.GenerateOtpResponse;
import com.cbd.otpgateway.services.GenerateOtpService;

import jakarta.validation.Valid;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(path = "api/v1/otp/generate")
public class GenerateOtpController {
    private final GenerateOtpService generateOtpService;
    private static final Logger logger = LoggerFactory.getLogger(GenerateOtpController.class);
    private final ObjectMapper objectMapper;

    @Autowired
    public GenerateOtpController(GenerateOtpService generateOtpService, ObjectMapper objectMapper) {
        this.generateOtpService = generateOtpService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity<GenerateOtpResponse> generateOtp(@Valid @RequestBody GenerateOtpRequest generateOtpRequest) {
        LocalDateTime startService = LocalDateTime.now();
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String logId = startService.format(formatter);
        String reqLog;
        String resLog;
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try{
            reqLog = objectMapper.writeValueAsString(generateOtpRequest);
        } catch (JsonProcessingException e) {
            logger.error("Error when converting incoming request into string for logging");
            throw new GenerateOtpException("GENERAL ERROR", "9099", "", logId);
        }

        logger.info(
                "{} - Start to processing Generate OTP. Request:\n{}",
                logId,
                reqLog
        );

        GenerateOtpResponse generateOtpResponse = generateOtpService.generateOtp(generateOtpRequest, logId);

        LocalDateTime endService = LocalDateTime.now();
        Duration duration = Duration.between(startService, endService);
        Long serviceTime = duration.toMillis();

        try{
            resLog = objectMapper.writeValueAsString(generateOtpResponse);
        } catch (JsonProcessingException e) {
            logger.error("Error when converting service response into string for logging");
            throw new GenerateOtpException("GENERAL ERROR", "9099", "", logId);
        }

        logger.info(
                "{} - Finished process Generate OTP. Response:\n{}",
                logId,
                resLog
        );
        logger.info("Time elapsed: {}ms", serviceTime);

        return new ResponseEntity<>(generateOtpResponse, HttpStatus.OK);
    }
}
