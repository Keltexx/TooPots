package es.uji.ei102718cln.TooPots.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei102718cln.TooPots.model.*;



public final class InterestedRowMapper implements RowMapper<Interested> {
    public Interested mapRow(ResultSet rs, int rowNum) throws SQLException {
        Interested interested = new Interested();
        interested.setInterestedID(rs.getString("interestedID"));
        interested.setCustomerID(rs.getString("customerID"));
        interested.setActivityTypeID(rs.getString("activityTypeID"));
        
        return interested;
    }
}