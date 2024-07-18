package com.cbd.otpgateway.exceptions;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;

import com.cbd.otpgateway.controllers.GenerateOtpController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cbd.otpgateway.schemas.GenerateOtpResponse;

@ControllerAdvice
public class GlobalExceptionHandler{
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Autowired
    private ObjectMapper objectMapper;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        Map<String, Object> body = new HashMap<>();

        List<String> errors = e.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.toList());

        body.put("status", "FAILED");
        body.put("error_code", "9001");
        body.put("error_message", "INVALID REQUEST");
        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = {GenerateOtpException.class})
    public ResponseEntity<Object> handleGenerateOtpException(GenerateOtpException e) {
        String resLog;
        GenerateOtpResponse response = new GenerateOtpResponse(e.otpReqAttempt, "FAILED", e.errorCode, e.getMessage());

        try{
            resLog = objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException exc) {
            logger.error("Error when converting service response into string for logging");
            throw new GenerateOtpException("GENERAL ERROR", "9099", "", e.logId);
        }

        logger.info(
                "{} - Finished process Generate OTP. Response:\n{}",
                e.logId,
                resLog
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
