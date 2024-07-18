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
public class OtpActivity {
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
    private LocalDateTime updatedAt;
    private String phoneNumber;
    private String activityType;
    private Integer attempt;
    
    public OtpActivity() {}

    public OtpActivity(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String phoneNumber,
        String activityType,
        Integer attempt
    ) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.phoneNumber = phoneNumber;
        this.activityType = activityType;
        this.attempt = attempt;
    }

    public OtpActivity(
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String phoneNumber,
        String activityType,
        Integer attempt
    ) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.phoneNumber = phoneNumber;
        this.activityType = activityType;
        this.attempt = attempt;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }


    public Integer getAttempt() {
        return attempt;
    }

    public void setAttempt(Integer attempt) {
        this.attempt = attempt;
    }
}
