package es.uji.ei102718cln.TooPots.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei102718cln.TooPots.model.Activity;
import es.uji.ei102718cln.TooPots.model.Photos;

@Repository
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
				"INSERT INTO Photos(photosid,activityid) VALUES(?, ?)",
				photos.getPhotosId(),photos.getActivityId());

	}

	/* Esborra la foto de la base de dades */
	public void deleteActivity(Photos photos) {
		jdbcTemplate.update("DELETE from Photos where photosid=?", photos.getPhotosId());
	}

	public void deleteActivity(String photosId) {
		jdbcTemplate.update("DELETE from Photos where photosid=?", photosId);
	}

	
	public Photos getPhoto(String photosId) {
		try {
			return jdbcTemplate.queryForObject("SELECT * from Photos WHERE photosid=?", new PhotosRowMapper(),
					photosId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/* Obté tots les fotos. Torna una llista buida si no n'hi ha cap. */
	public List<Photos> getPhotos() {
		try {
			return jdbcTemplate.query("Select * from Photos",  new PhotosRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<Photos>();
		}
	}
	
	public List<Photos> getPhotos(String activityId) {
		try {
			return jdbcTemplate.query("Select photosid from Photos where activityid=?",  new PhotosRowMapper(), Integer.valueOf(activityId));
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<Photos>();
		}
	}
}

