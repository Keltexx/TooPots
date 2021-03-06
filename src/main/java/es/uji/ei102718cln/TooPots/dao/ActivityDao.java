package es.uji.ei102718cln.TooPots.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei102718cln.TooPots.model.Activity;
import es.uji.ei102718cln.TooPots.model.Photos;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository // En Spring els DAOs van anotats amb @Repository
public class ActivityDao {

	private JdbcTemplate jdbcTemplate;

	// Obté el jdbcTemplate a partir del Data Source
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/* Afegeix l' activity a la base de dades */
	public void addActivity(Activity activity) {
		jdbcTemplate.update(
				"INSERT INTO Activity(place,name,schedule,hour,duration,description,pricebyperson,numberofpeople,instructorid,activitytypeid, photo) VALUES(?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				activity.getPlace(), activity.getName(), activity.getSchedule(), activity.getHour(), activity.getDuration(),
				activity.getDescription(), activity.getPriceByPerson(), activity.getNumberOfPeople(),
				activity.getInstructorId(), activity.getActivityTypeId(), activity.getPhoto());

	}
	public void addPhoto(Photos photo) {
		jdbcTemplate.update(
				"INSERT INTO Photos(photosid) VALUES(?)",
				photo.getPhotosId());

	}

	/* Esborra la activity de la base de dades */
	public void deleteActivity(Activity activity) {
		jdbcTemplate.update("DELETE from Activity where activityId=?", activity.getActivityId());
	}

	public void deleteActivity(String activityid) {
		jdbcTemplate.update("DELETE from Activity where activityId=?", Integer.valueOf(activityid));
	}

	/*
	 * Actualitza els atributs del activity (excepte la clau primària)
	 */
	public void updateActivity(Activity activity) {
		jdbcTemplate.update(
				"UPDATE activity  SET place=?, name=?, schedule=?, hour=?, duration=?, description=?, priceByPerson=?, numberOfPeople=?, instructorId=?, activityTypeId=? where activityid=?",
				activity.getPlace(), activity.getName(), activity.getSchedule(), activity.getHour(), activity.getDuration(),
				activity.getDescription(), activity.getPriceByPerson(), activity.getNumberOfPeople(),
				activity.getInstructorId(), activity.getActivityTypeId(),activity.getActivityId());
	}

	/* Obté l activitat amb el nom donat. Torna null si no existeix. */
	public Activity getActivity(String activityId) {
		try {
			return jdbcTemplate.queryForObject("SELECT * from Activity WHERE activityId=?", new ActivityRowMapper(),
					Integer.valueOf(activityId));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/* Obté tots les activitats. Torna una llista buida si no n'hi ha cap. */
	public List<Activity> getActivities() {
		try {
			return jdbcTemplate.query("SELECT * from Activity", new ActivityRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<Activity>();
		}
	}
	
	public List<Activity> getActivities(String activityTypeId) {
		try {
			return jdbcTemplate.query("SELECT * from Activity where activitytypeid=?", new ActivityRowMapper(), activityTypeId);
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<Activity>();
		}
	}
	
	public void updatePeople(int people, int activityId) {
		jdbcTemplate.update(
				"UPDATE activity  SET numberOfPeople=? where activityid=?",
				people,activityId);
	}
	
}
