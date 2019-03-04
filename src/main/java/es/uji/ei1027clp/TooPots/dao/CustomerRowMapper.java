package es.uji.ei1027clp.TooPots.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import es.uji.ei1027clp.TooPots.model.*;
import org.springframework.jdbc.core.RowMapper;


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