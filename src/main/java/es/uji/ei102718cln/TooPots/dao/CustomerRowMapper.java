package es.uji.ei102718cln.TooPots.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei102718cln.TooPots.model.*;


public final class CustomerRowMapper implements RowMapper<Customer> {
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setName(rs.getString("name"));
        customer.setNif(rs.getString("nif"));
        customer.setEmail(rs.getString("email"));
        customer.setGender(rs.getString("gender"));
        customer.setBirthDate(rs.getObject("birthDate", LocalDate.class));
        customer.setPassword(rs.getString("password"));
        return customer;
        
    }
}