package es.uji.ei102718cln.TooPots.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei102718cln.TooPots.model.ActivityType;

public final class ActivityTypeRowMapper implements RowMapper<ActivityType> {
	public ActivityType mapRow(ResultSet rs, int rowNum) throws SQLException {
		ActivityType activityType = new ActivityType();
		activityType.setActivityTypeId(rs.getInt("activitytypeid"));
		activityType.setNameType(rs.getString("nameType"));
		activityType.setRiskLevel(rs.getString("riskLevel"));
		activityType.setDescription(rs.getString("description"));

        return activityType;
	}

}
