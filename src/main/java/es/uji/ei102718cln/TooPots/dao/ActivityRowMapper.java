package es.uji.ei102718cln.TooPots.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei102718cln.TooPots.model.Activity;

public final class ActivityRowMapper implements RowMapper<Activity> {
	public Activity mapRow(ResultSet rs, int rowNum) throws SQLException {
		Activity activity = new Activity();
        activity.setActivityId(rs.getInt("activityId"));
        activity.setPlace(rs.getString("place"));
        activity.setName(rs.getString("name"));
        activity.setSchedule(rs.getObject("schedule", LocalDate.class));
        activity.setDuration(rs.getTime("duration"));
        activity.setDescription(rs.getString("description"));
        activity.setPriceByPerson(rs.getInt("priceByPerson"));
        activity.setNumberOfPeople(rs.getInt("numberOfPeople"));
        activity.setInstructorId(rs.getString("instructorId"));
        activity.setActivityTypeId(rs.getInt("activityTypeId"));
        activity.setPhoto(rs.getString("photo"));
        return activity;
	}

}
