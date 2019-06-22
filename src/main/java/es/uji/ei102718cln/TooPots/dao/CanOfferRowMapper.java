package es.uji.ei102718cln.TooPots.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei102718cln.TooPots.model.CanOffer;


public final class CanOfferRowMapper implements RowMapper<CanOffer>{
	public CanOffer mapRow(ResultSet rs, int rowNum) throws SQLException {
		CanOffer canOffer = new CanOffer();
		canOffer.setCanOfferId(rs.getInt("canOfferId"));
		canOffer.setInstructorId(rs.getString("instructorId"));
		canOffer.setActivityTypeId(rs.getString("activityTypeId"));

        return canOffer;
	}

}
