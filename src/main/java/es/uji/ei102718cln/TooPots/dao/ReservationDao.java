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
public class ReservationDao {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDateSource(DataSource dataSource) {
		jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	public void addReservation(Reservation reservation) {
		jdbcTemplate.update("INSERT INTO Reservation VALUES(?, ?, ?, ?, ?, ? , ? , ? )",
                reservation.getReservationID(), reservation.getBookingDate(), reservation.getActivityDate(),
                reservation.getPriceByPerson(), reservation.getTotalPrice(), reservation.getNumberOfPersons(),
                reservation.getActivityID(),reservation.getCustomerID());
	}
	 public void deleteReservation(Reservation reservation) {
	        jdbcTemplate.update("DELETE from Reservation where reservationID=?", reservation.getReservationID());
	    }

	    
	    public void updateReservation(Reservation reservation) {
	        jdbcTemplate.update("UPDATE Reservation SET bookingDate=?, bookingDate=?, activityDate=?, "
	        		+ "priceByPerson=?, totalPrice=?, numberOfPersons=?, activityID=?,customerID=? where reservationID=?",
	        		reservation.getBookingDate(), reservation.getActivityDate(),
	                reservation.getPriceByPerson(), reservation.getTotalPrice(), reservation.getNumberOfPersons(),
	                reservation.getActivityID(),reservation.getCustomerID(), reservation.getReservationID()
	                );
	    }

	    
	    public Reservation getReservation(String id) {
	        try {
	            return jdbcTemplate.queryForObject("SELECT * from Reservation WHERE reservationID=?",
	                    new ReservationRowMapper(), id);
	        }
	        catch(EmptyResultDataAccessException e) {
	            return null;
	        }
	    }
}