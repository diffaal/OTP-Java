package com.cbd.otpgateway.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class OtpList {
    @Id
    @SequenceGenerator(
        name = "student_sequence",
        sequenceName = "student_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "student_sequence"
    )
    private Long id;
    private LocalDateTime createdAt;
    private String phoneNumber;
    private String otpCode;
    private LocalDateTime expiredTime;
    private Integer isUsed;
    
    public OtpList() {}

    public OtpList(
        Long id,
        LocalDateTime createdAt,
        String phoneNumber,
        String otpCode,
        LocalDateTime expiredTime,
        Integer isUsed
    ) {
        this.id = id;
        this.createdAt = createdAt;
        this.phoneNumber = phoneNumber;
        this.otpCode = otpCode;
        this.expiredTime = expiredTime;
        this.isUsed = isUsed;
    }

    public OtpList(
        LocalDateTime createdAt,
        String phoneNumber,
        String otpCode,
        LocalDateTime expiredTime,
        Integer isUsed
    ) {
        this.createdAt = createdAt;
        this.phoneNumber = phoneNumber;
        this.otpCode = otpCode;
        this.expiredTime = expiredTime;
        this.isUsed = isUsed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public LocalDateTime getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(LocalDateTime expiredTime) {
        this.expiredTime = expiredTime;
    }

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }
    
}
