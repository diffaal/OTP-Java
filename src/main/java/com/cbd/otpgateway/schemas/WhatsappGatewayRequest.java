package com.cbd.otpgateway.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WhatsappGatewayRequest {
    @JsonProperty("channelId")
    private String channelId;
    @JsonProperty("partnerId")
    private String partnerId;
    @JsonProperty("projectType")
    private String projectType;
    @JsonProperty("mobileNum")
    private String mobileNum;
    @JsonProperty("param1")
    private String param1;
    @JsonProperty("param2")
    private String param2;
    @JsonProperty("param3")
    private String param3;
    @JsonProperty("jenis_rekening")
    private String jenisRekening;

    public WhatsappGatewayRequest() {
    }

    public WhatsappGatewayRequest(String channelId, String partnerId, String projectType, String mobileNum, String param1, String param2, String param3, String jenisRekening) {
        this.channelId = channelId;
        this.partnerId = partnerId;
        this.projectType = projectType;
        this.mobileNum = mobileNum;
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
        this.jenisRekening = jenisRekening;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getParam3() {
        return param3;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }

    public String getJenisRekening() {
        return jenisRekening;
    }

    public void setJenisRekening(String jenisRekening) {
        this.jenisRekening = jenisRekening;
    }
}
