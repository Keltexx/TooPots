package es.uji.ei102718cln.TooPots.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei102718cln.TooPots.model.ActivityType;

public final class ActivityTypeRowMapper implements RowMapper<ActivityType> {
	public ActivityType mapRow(ResultSet rs, int rowNum) throws SQLException {
		ActivityType activiyType = new ActivityType();
		activiyType.setId(rs.getString("id"));
		activiyType.setNameType(rs.getString("nameType"));
		activiyType.setRiskLevel(rs.getString("RiskLevel"));
		activiyType.setDescription(rs.getString("description"));

        return activiyType;
	}

}
