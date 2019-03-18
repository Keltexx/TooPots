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

	/* Afegeix l' activity a la base de dades */
	public void addInstructor(Instructor instructor) {
		jdbcTemplate.update("INSERT INTO Instructor VALUES(?, ?, ?, ?, ?, ?,)", 
				instructor.getName(),instructor.getNif(),instructor.getEmail(),instructor.getAddress(),instructor.getCertificates(),instructor.getBankAccount());
	}

	/* Esborra el nadador de la base de dades */
	public void deleteInstructor(Instructor instructor) {
		jdbcTemplate.update("DELETE from instructor where name=?", instructor.getName());
	}

	/*
	 * Actualitza els atributs del activity (excepte la clau primària)
	 */
	public void updateInstructor(Instructor instructor) {
		jdbcTemplate.update(
				"UPDATE instructor  SET name=?,email=?,address=?,certificates=?,bankAccount=? where nif=?",
				instructor.getName(),instructor.getEmail(),instructor.getAddress(),instructor.getCertificates(),instructor.getBankAccount(),instructor.getNif());
	}

	/* Obté el nadador amb el nom donat. Torna null si no existeix. */
	public Instructor getInstructor(String nameInstructor) {
		try {
			return jdbcTemplate.queryForObject("SELECT * from instructor WHERE name=?", new InstructorRowMapper(),
					nameInstructor);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/* Obté tots les activitats. Torna una llista buida si no n'hi ha cap. */
	public List<Instructor> getInstructors() {
		try {
			return jdbcTemplate.query("SELECT * from instructor", new InstructorRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<Instructor>();
		}
	}
}
