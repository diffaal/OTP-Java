package com.cbd.otpgateway.repositories;

import com.cbd.otpgateway.exceptions.GenerateOtpException;
import com.cbd.otpgateway.models.OtpList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class OtpListRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertOtpList(OtpList otpList, String logId) {
        String sql = "INSERT INTO OTP_LIST(CREATED_AT, PHONE_NUMBER, OTP_CODE, EXPIRED_TIME, IS_USED) " +
                "VALUES(?, ?, ?, ?, ?)";

        Object[] params = new Object[] {
                otpList.getCreatedAt(),
                otpList.getPhoneNumber(),
                otpList.getOtpCode(),
                otpList.getExpiredTime(),
                otpList.getIsUsed()
        };

        try {
            jdbcTemplate.update(sql, params);
        } catch (DataAccessException e) {
            throw new GenerateOtpException("DATABASE ERROR", "9088", "", logId);
        }
    }

    public OtpList getLastOtpCode(String phoneNumber, String logId) {
        OtpList result;
        String sql = "SELECT ID, OTP_CODE, EXPIRED_TIME, IS_USED FROM OTP_LIST " +
                "WHERE PHONE_NUMBER = ? ORDER BY CREATED_AT DESC";

        // Define your RowMapper implementation
        RowMapper<OtpList> rowMapper = new RowMapper<OtpList>() {
            @Override
            public OtpList mapRow(ResultSet rs, int rowNum) throws SQLException {
                try {
                    OtpList obj = new OtpList();
                    obj.setId(rs.getLong("ID"));
                    obj.setOtpCode(rs.getString("OTP_CODE"));
                    obj.setExpiredTime(rs.getTimestamp("EXPIRED_TIME").toLocalDateTime());
                    obj.setIsUsed(rs.getInt("IS_USED"));
                    // Map other columns as needed
                    return obj;
                } catch (SQLException e) {
                    throw new GenerateOtpException("DATABASE ERROR", "9088", "", logId);
                }

            }
        };

        try {
            result = jdbcTemplate.queryForObject(sql, rowMapper, phoneNumber);
        } catch (DataAccessException e) {
            throw new GenerateOtpException("DATABASE ERROR", "9088", "", logId);
        }

        return result;
    }

    public void updateOtpCodeUsed(Long id, String logId) {
        String sql = "UPDATE OTP_LIST SET " +
                "IS_USED = 1 " +
                "WHERE ID = ?";

        Object[] params = new Object[] {id};

        try {
            jdbcTemplate.update(sql, params);
        } catch (DataAccessException e) {
            throw new GenerateOtpException("DATABASE ERROR", "9088", "", logId);
        }
    }
}
