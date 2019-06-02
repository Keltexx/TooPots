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
public class InstructorDao {

	private JdbcTemplate jdbcTemplate;

	// Obté el jdbcTemplate a partir del Data Source
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/* Afegeix l'instructor a la base de dades */
	public void addInstructor(Instructor instructor) {
		jdbcTemplate.update("INSERT INTO Instructor VALUES(?, ?, ?, ?, ?, ?, ?,?,?)", instructor.getNif(),
				instructor.getName(), instructor.getEmail(), instructor.getAddress(), instructor.getBankAccount(),
				instructor.getPhoto(), instructor.getPassword(), instructor.getState(), instructor.getPhoto());
	}

	/* Esborra el instructor de la base de dades */
	public void deleteInstructor(Instructor instructor) {
		jdbcTemplate.update("DELETE from Instructor where nif=?", instructor.getNif());
	}

	public void deleteInstructor(String nif) {
		jdbcTemplate.update("DELETE from Instructor where nif=?", nif);
	}
	/*
	 * Actualitza els atributs del instructor (excepte la clau primària)
	 */
	public void updateInstructor(Instructor instructor) {
		jdbcTemplate.update("UPDATE Instructor  SET name=?,email=?,address=?,bankAccount=?, photo=? where nif=?",
				instructor.getName(), instructor.getEmail(), instructor.getAddress(),
				instructor.getBankAccount(), instructor.getPhoto(),instructor.getNif());
	}

	/* Obté el instructor amb el nom donat. Torna null si no existeix. */
	public Instructor getInstructor(String nif) {
		try {
			return jdbcTemplate.queryForObject("SELECT * from Instructor WHERE nif=?", new InstructorRowMapper(),
					nif);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/* Obté tots els instructors. Torna una llista buida si no n'hi ha cap. */
	public List<Instructor> getInstructors() {
		try {
			return jdbcTemplate.query("SELECT * from Instructor", new InstructorRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<Instructor>();
		}
	}
	
	public void updateInstructorStateAccept(String nif) {
		jdbcTemplate.update("UPDATE instructor SET state=? where nif=?",
				"accepted", nif);
	}
	
	public void updateInstructorStateReject(String nif) {
		jdbcTemplate.update("UPDATE instructor SET state=? where nif=?",
				"rejected", nif);
	}
}
