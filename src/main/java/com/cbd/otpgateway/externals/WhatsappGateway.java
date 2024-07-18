package com.cbd.otpgateway.externals;

import com.cbd.otpgateway.configs.AppConfig;
import com.cbd.otpgateway.exceptions.GenerateOtpException;
import com.cbd.otpgateway.schemas.WhatsappGatewayRequest;
import com.cbd.otpgateway.schemas.WhatsappGatewayResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.*;

import java.util.HashMap;
import java.util.Map;

@Component
public class WhatsappGateway {
    private final AppConfig appConfig;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(WhatsappGateway.class);

    @Autowired
    public WhatsappGateway(AppConfig appConfig, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.appConfig = appConfig;
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }

    public void sendMessage(WhatsappGatewayRequest whatsappGatewayRequest, String logId) {
        String reqLog;
        String resLog;
        ResponseEntity<Map<String, Object>> responseEntity;
        WhatsappGatewayResponse responseObj;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        HttpEntity<WhatsappGatewayRequest> requestEntity = new HttpEntity<>(whatsappGatewayRequest, headers);

        try{
            reqLog = objectMapper.writeValueAsString(whatsappGatewayRequest);
        } catch (JsonProcessingException e) {
            logger.error("Error when converting incoming request into string for logging");
            throw new GenerateOtpException("GENERAL ERROR", "9099", "", logId);
        }

        logger.info("{} - Whatsapp Gateway Send Message URL: {}", logId, appConfig.getWaGatewayUrl());
        logger.info("{} - Whatsapp Gateway Send Message Request Body:\n{}", logId, reqLog);
        try{
            responseEntity = restTemplate.exchange(
                    appConfig.getWaGatewayUrl(),
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<>() {});

        } catch (HttpClientErrorException e) {
            throw new GenerateOtpException("(WA GATEWAY) REQUEST CLIENT ERROR", "9006", "", logId);
        } catch (HttpServerErrorException e) {
            throw new GenerateOtpException("(WA GATEWAY) REQUEST SERVER ERROR", "9007", "", logId);
        } catch (ResourceAccessException e) {
            throw new GenerateOtpException("(WA GATEWAY) ACCESS ERROR TO WA GATEWAY SERVICE", "9005", "", logId);
        } catch (RestClientException e) {
            throw new GenerateOtpException("(WA GATEWAY) GENERAL REQUEST ERROR TO WA GATEWAY SERVICE", "9004", "", logId);
        }

        Map<String, Object> responseBody = responseEntity.getBody();

        try{
            resLog = objectMapper.writeValueAsString(responseBody);
        } catch (JsonProcessingException e) {
            logger.error("Error when converting service response into string for logging");
            throw new GenerateOtpException("GENERAL ERROR", "9099", "", logId);
        }

        logger.info("{} - Whatsapp Gateway Send Message Response Body:\n{}", logId, resLog);

        String errorCode = responseBody.get("errorCode").toString();
        String errorMessage = responseBody.get("errorMessage").toString();
        if (!errorCode.equals("200")) {
            throw new GenerateOtpException("(WA GATEWAY) " + errorMessage, "9009", "", logId);
        }
    }
}
