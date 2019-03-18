package es.uji.ei102718cln.TooPots.dao;

import org.springframework.stereotype.Repository;

import es.uji.ei102718cln.TooPots.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDao {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDateSource(DataSource dataSource) {
		jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	public void addCustomer(Customer customer) {
		jdbcTemplate.update("INSERT INTO Customer VALUES(?, ?, ?, ?, ?)",
                customer.getName(), customer.getNif(), customer.getEmail(),
                customer.getGender(),customer.getBirthDate());
	}
	 public void deleteCustomer(Customer customer) {
	        jdbcTemplate.update("DELETE from Customer where name=?", customer.getName());
	    }
	 
	 public void deleteCustomer(String customer) {
		 jdbcTemplate.update("DELETE from Customer where name=?", customer);
	 }


	    public void updateCustomer(Customer customer) {
	        jdbcTemplate.update("UPDATE Customer SET nif=?, email=?, gender=?, birthDate=? where name=?",
	                customer.getNif(), customer.getEmail(),
	                customer.getGender(),       customer.getBirthDate(), customer.getName());
	    }


	    public Customer getCustomer(String customerName) {
	        try {
	            return jdbcTemplate.queryForObject("SELECT * from Customer WHERE name=?",
	                    new CustomerRowMapper(), customerName);
	        }
	        catch(EmptyResultDataAccessException e) {
	            return null;
	        }
	    }
	    public List<Customer> getCustomers() {
	    	try {
	    		return jdbcTemplate.query("Select * from Customer", new CustomerRowMapper());
	    	}catch( EmptyResultDataAccessException e) {
	    		return new ArrayList<Customer>();
	    	}
	    	
	    }
}
