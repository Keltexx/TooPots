package es.uji.ei102718cln.TooPots.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei102718cln.TooPots.model.Login;

public final class LoginRowMapper implements RowMapper<Login> {
    public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
        Login login = new Login();
        login.setUsuario(rs.getString("usuario"));
        login.setPassword(rs.getString("password"));
        login.setRol(rs.getString("rol"));
        return login;
    }
}
