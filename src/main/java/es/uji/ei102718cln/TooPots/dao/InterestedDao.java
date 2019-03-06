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
public class InterestedDao {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDateSource(DataSource dataSource) {
		jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	public void addInterested(Interested interested) {
		jdbcTemplate.update("INSERT INTO Interested VALUES(?, ?, ?)",
                interested.getInterestedID(), interested.getCustomerID(), interested.getActivityTypeID());
	}
	 public void deleteInterested(Interested interested) {
	        jdbcTemplate.update("DELETE from Interested where reservationID=?", interested.getActivityTypeID());
	    }


	    public void updateInterested(Interested interested) {
	        jdbcTemplate.update("UPDATE Interested SET customerID=?, activityTypeID=? where interestedID=?",
	                interested.getCustomerID(), interested.getActivityTypeID(),interested.getInterestedID());
	    }


	    public Interested getInterested(String id) {
	        try {
	            return jdbcTemplate.queryForObject("SELECT * from Interested WHERE interestedID=?",
	                    new InterestedRowMapper(), id);
	        }
	        catch(EmptyResultDataAccessException e) {
	            return null;
	        }
	    }
}