package es.uji.ei102718cln.TooPots.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei102718cln.TooPots.model.CanOffer;

@Repository
public class CanOfferDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDateSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void addCanOffer(CanOffer canOffer) {
		jdbcTemplate.update("INSERT INTO CanOffer(instructorId,activityTypeId) VALUES( ?, ?)",
				canOffer.getInstructorId(), canOffer.getActivityTypeId());
	}

	public void deleteCanOffer(CanOffer canOffer) {
		jdbcTemplate.update("DELETE from CanOffer where canofferid=?", canOffer.getCanOfferId());
	}

	public void deleteCanOffer2(String instructorId ,String activityTypeId) {
		jdbcTemplate.update("DELETE from CanOffer where instructorid=? and activitytypeid=?", instructorId, activityTypeId);
	}
	public void deleteCanOffer(int canOffer) {
		jdbcTemplate.update("DELETE from CanOffer where canofferid=?", canOffer);
	}

	/*
	 * Actualitza els atributs (excepte el id, que és la clau primària)
	 */
	public void updateCanOffer(CanOffer canOffer) {
		jdbcTemplate.update("UPDATE CanOffer  SET instructorid=?, activitytypeid=? where canofferid=?",
				canOffer.getInstructorId(), canOffer.getActivityTypeId(), canOffer.getCanOfferId());
	}

	public CanOffer getCanOffer(int canofferid) {
		try {
			return jdbcTemplate.queryForObject("SELECT * from CanOffer WHERE canofferid=?", new CanOfferRowMapper(),
					canofferid);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<CanOffer> getCanOffers() {
		try {
			return jdbcTemplate.query("SELECT * from CanOffer", new CanOfferRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<CanOffer>();
		}
	}
	
	public List<CanOffer> getActivityInstructor(String instructorid){
		
		try {
			return jdbcTemplate.query("SELECT * from CanOffer where instructorid=?", new CanOfferRowMapper(), instructorid);
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<CanOffer>();
		}
	}
}
