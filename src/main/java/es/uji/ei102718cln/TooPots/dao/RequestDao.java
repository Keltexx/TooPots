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
public class RequestDao {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDateSource(DataSource dataSource) {
		jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	public void addRequest(Request request) {
		jdbcTemplate.update("INSERT INTO Request VALUES(?, ?)",
                request.getState(),request.getState());
	}
	 public void deleteRequest(Request request) {
	        jdbcTemplate.update("DELETE from Request where name=?", request.getRequestID());
	    }
	 
	 public void deleteRequest(String request) {
		 jdbcTemplate.update("DELETE from Request where name=?", request);
	 }


	    public void updateRequest(Request request) {
	        jdbcTemplate.update("UPDATE Request SET state=?, certificateAtached=? where requestID=?",
	        		request.getState(), request.getCertificateAttached(), request.getRequestID());
	                
	    }


	    public Customer getRequest(String requestName) {
	        try {
	            return jdbcTemplate.queryForObject("SELECT * from Request WHERE requestID=?",
	                    new RequestRowMapper(), requestName);
	        }
	        catch(EmptyResultDataAccessException e) {
	            return null;
	        }
	    }
	    public List<Request> getRequests() {
	    	try {
	    		return jdbcTemplate.query("Select * from Request", new RequestRowMapper());
	    	}catch( EmptyResultDataAccessException e) {
	    		return new ArrayList<Request>();
	    	}
	    	
	    }
}