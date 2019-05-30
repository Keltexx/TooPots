package es.uji.ei102718cln.TooPots.dao;

import org.springframework.stereotype.Repository;

import es.uji.ei102718cln.TooPots.model.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDateSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void addReservation(Reservation reservation) {
		jdbcTemplate.update(
				"INSERT INTO Reservation(bookingdate, activitydate, pricebyperson, totalprice, numberofpersons, activityid, customerid) VALUES(?, ?, ?, ?, ?, ? , ?  )",
				reservation.getBookingDate(), reservation.getActivityDate(), reservation.getPriceByPerson(),
				reservation.getTotalPrice(), reservation.getNumberOfPersons(), reservation.getActivityID(),
				reservation.getCustomerID());
	}

	public void deleteReservation(Reservation reservation) {
		jdbcTemplate.update("DELETE from Reservation where reservationID=?", reservation.getReservationID());
	}

	public void deleteReservation(String reservationid) {
		jdbcTemplate.update("DELETE from Reservation where reservationID=?", Integer.valueOf(reservationid));
	}

	public void updateReservation(Reservation reservation) {
		jdbcTemplate.update("UPDATE Reservation SET bookingDate=?, bookingDate=?, activityDate=?, "
				+ "priceByPerson=?, totalPrice=?, numberOfPersons=?, activityID=?,customerID=? where reservationID=?",
				reservation.getBookingDate(), reservation.getActivityDate(), reservation.getPriceByPerson(),
				reservation.getTotalPrice(), reservation.getNumberOfPersons(), reservation.getActivityID(),
				reservation.getCustomerID(), reservation.getReservationID());
	}

	public Reservation getReservation(String id) {
		try {
			return jdbcTemplate.queryForObject("SELECT * from Reservation WHERE reservationID=?",
					new ReservationRowMapper(), Integer.valueOf(id));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<Reservation> getReservations() {
		try {
			return jdbcTemplate.query("SELECT * from Reservation", new ReservationRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<Reservation>();
		}
	}
	
	public List<Reservation> getReservationsCustomer(String customerid) {
		try {
			return jdbcTemplate.query("SELECT * from Reservation where customerid=?", new ReservationRowMapper(), customerid);
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<Reservation>();
		}
	}
}