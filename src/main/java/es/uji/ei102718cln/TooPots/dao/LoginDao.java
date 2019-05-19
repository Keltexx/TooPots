package es.uji.ei102718cln.TooPots.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei102718cln.TooPots.model.Login;

@Repository
public class LoginDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Login getLogin(String usuario, String contraseña) {
		try {
			return jdbcTemplate.queryForObject("SELECT * from Login WHERE usuario=? AND contraseña=?",
					new LoginRowMapper(), usuario, contraseña);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}