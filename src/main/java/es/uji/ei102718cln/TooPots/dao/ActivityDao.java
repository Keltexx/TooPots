package es.uji.ei102718cln.TooPots.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei102718cln.TooPots.model.*;

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
		jdbcTemplate.update("INSERT INTO Activity VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", activity.getId(),
				activity.getPlace(), activity.getName(), activity.getSchedule(), activity.getDuration(),
				activity.getDescription(), activity.getPriceByPerson(), activity.getNumberOfPeople(),
				activity.getInstructorId(), activity.getActivityTypeId());
	}

	/* Esborra la activity de la base de dades */
	public void deleteActivity(Activity activity) {
		jdbcTemplate.update("DELETE from Activity where id=?", activity.getId());
	}
	
	 public void deleteActivity(String activity) {
		 jdbcTemplate.update("DELETE from Activity where name=?", activity);
	 }

	/*
	 * Actualitza els atributs del activity (excepte la clau primària)
	 */
	public void updateActivity(Activity activity) {
		jdbcTemplate.update(
				"UPDATE activity  SET (place=?, name=?, schedule=?, duration=?, description=?, priceByPerson=?, numberOfPeople=?, instructorId=?, activityTypeId=?)",
				activity.getPlace(), activity.getName(), activity.getSchedule(), activity.getDuration(),
				activity.getDescription(), activity.getPriceByPerson(), activity.getNumberOfPeople(),
				activity.getInstructorId(), activity.getActivityTypeId());
	}

	/* Obté el nadador amb el nom donat. Torna null si no existeix. */
	public Activity getActivity(String nameActivity) {
		try {
			return jdbcTemplate.queryForObject("SELECT * from Activity WHERE name=?", new ActivityRowMapper(),
					nameActivity);
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
}
