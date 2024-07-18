package com.cbd.otpgateway.repositories;

import com.cbd.otpgateway.exceptions.GenerateOtpException;
import com.cbd.otpgateway.models.OtpActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

@Repository
public class OtpActivityRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public OtpActivity getLastOtpActivity(String phoneNumber, String activityType, String logId) {
        OtpActivity result;
        String sql = "SELECT ID, UPDATED_AT, ATTEMPT FROM OTP_ACTIVITY " +
                "WHERE PHONE_NUMBER = ? AND ACTIVITY_TYPE = ? ORDER BY UPDATED_AT DESC";

        // Define your RowMapper implementation
        RowMapper<OtpActivity> rowMapper = new RowMapper<OtpActivity>() {
            @Override
            public OtpActivity mapRow(ResultSet rs, int rowNum) throws SQLException {
                try {
                    OtpActivity obj = new OtpActivity();
                    obj.setId(rs.getLong("ID"));
                    obj.setUpdatedAt(rs.getTimestamp("UPDATED_AT").toLocalDateTime());
                    obj.setAttempt(rs.getInt("ATTEMPT"));
                    // Map other columns as needed
                    return obj;
                } catch (SQLException e) {
                    throw new GenerateOtpException("DATABASE ERROR", "9088", "", logId);
                }

            }
        };

        try {
            result = jdbcTemplate.queryForObject(sql, rowMapper, phoneNumber, activityType);
        } catch (DataAccessException e) {
            throw new GenerateOtpException("DATABASE ERROR", "9088", "", logId);
        }

        return result;
    }

    public Long insertOtpActivity(OtpActivity otpActivity, String logId) {
        long generatedId;
        String sql = "INSERT INTO OTP_ACTIVITY(CREATED_AT, UPDATED_AT, PHONE_NUMBER, ACTIVITY_TYPE, ATTEMPT) " +
                "VALUES(?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setObject(1, otpActivity.getCreatedAt());
                ps.setObject(2, otpActivity.getUpdatedAt());
                ps.setObject(3, otpActivity.getPhoneNumber());
                ps.setObject(4, otpActivity.getActivityType());
                ps.setObject(5, otpActivity.getAttempt());
                // Set other parameters as needed
                return ps;
            }, keyHolder);
        } catch (DataAccessException e) {
            throw new GenerateOtpException("DATABASE ERROR", "9088", "", logId);
        }

        try {
            generatedId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        } catch (NullPointerException e) {
            throw new GenerateOtpException("DATABASE ERROR", "9088", "", logId);
        }

        return generatedId;
    }

    public void updateOtpActivity(OtpActivity otpActivity, String logId){
        String sql = "UPDATE OTP_ACTIVITY SET " +
                "UPDATED_AT = ? " +
                "ATTEMPT = ? " +
                "WHERE ID = ?";

        Object[] params = new Object[] {otpActivity.getUpdatedAt(), otpActivity.getAttempt(), otpActivity.getId()};

        try {
            jdbcTemplate.update(sql, params);
        } catch (DataAccessException e) {
            throw new GenerateOtpException("DATABASE ERROR", "9088", "", logId);
        }
    }
}
