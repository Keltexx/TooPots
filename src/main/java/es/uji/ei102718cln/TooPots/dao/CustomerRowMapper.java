package es.uji.ei102718cln.TooPots.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei102718cln.TooPots.model.*;


public final class CustomerRowMapper implements RowMapper<Customer> {
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setName(rs.getString("name"));
        customer.setBirthDate(rs.getDate("birthDate"));
        customer.setEmail(rs.getString("email"));
        customer.setNif(rs.getString("nif"));
        customer.setGender(rs.getString("gender"));
        return customer;
        
    }
}