package es.uji.ei102718cln.TooPots.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import es.uji.ei102718cln.TooPots.model.Activity;
import es.uji.ei102718cln.TooPots.model.Photos;

public class PhotosDao {
	private JdbcTemplate jdbcTemplate;

	// Obté el jdbcTemplate a partir del Data Source
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/* Afegeix la foto a la base de dades */
	public void addPhoto(Photos photos) {
		jdbcTemplate.update(
				"INSERT INTO Activity(photosid,activityid) VALUES(?, ?)",
				photos.getPhoto(),photos.getActivityId());

	}

	/* Esborra la foto de la base de dades */
	public void deleteActivity(Photos photos) {
		jdbcTemplate.update("DELETE from Photos where photosid=?", photos.getPhoto());
	}

	public void deleteActivity(String photoid) {
		jdbcTemplate.update("DELETE from Photos where photoid=?", Integer.valueOf(photoid));
	}

	/*
	 * Actualitza els atributs del activity (excepte la clau primària)
	 */
	public void updateActivity(Activity activity) {
		jdbcTemplate.update(
				"UPDATE activity  SET place=?, name=?, schedule=?, duration=?, description=?, priceByPerson=?, numberOfPeople=?, instructorId=?, activityTypeId=?, photo=? where activityid=?",
				activity.getPlace(), activity.getName(), activity.getSchedule(), activity.getDuration(),
				activity.getDescription(), activity.getPriceByPerson(), activity.getNumberOfPeople(),
				activity.getInstructorId(), activity.getActivityTypeId(), activity.getPhoto(),activity.getActivityId());
	}

	/* Obté l activitat amb el nom donat. Torna null si no existeix. */
	public Photos getPhoto(String photoId) {
		try {
			return jdbcTemplate.queryForObject("SELECT * from Photos WHERE photosid=?", new PhotosRowMapper(),
					Integer.valueOf(photoId));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/* Obté tots les fotos. Torna una llista buida si no n'hi ha cap. */
	public List<Photos> getPhotos(Activity activity) {
		try {
			return jdbcTemplate.query("Select * from Request where instructorid=?",  new PhotosRowMapper(),activity);
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<Photos>();
		}
	}
}

