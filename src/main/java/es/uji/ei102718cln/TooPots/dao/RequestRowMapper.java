package es.uji.ei102718cln.TooPots.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei102718cln.TooPots.model.*;



public final class RequestRowMapper implements RowMapper<Request> {
    public Request mapRow(ResultSet rs, int rowNum) throws SQLException {
        Request request = new Request();
        request.setRequestID(rs.getInt("requestID"));
        request.setCertificateAttached(rs.getString("certificateAtached"));
        request.setState(rs.getString("state"));
        request.setInstructorID(rs.getString("instructorID"));
        request.setActivityTypeId(rs.getString("activityTypeId"));
        return request;
    }
}