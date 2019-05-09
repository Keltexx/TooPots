package es.uji.ei102718cln.TooPots.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei102718cln.TooPots.model.Instructor;

public final class InstructorRowMapper implements RowMapper<Instructor> {
	public Instructor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Instructor instructor = new Instructor();
		instructor.setNif(rs.getString("nif"));
		instructor.setName(rs.getString("name"));
		instructor.setEmail(rs.getString("email"));
		instructor.setBankAccount(rs.getString("bankAccount"));
		instructor.setAddress(rs.getString("address"));
		instructor.setPhoto(rs.getString("photo"));
		return instructor;

	}
}