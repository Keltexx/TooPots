package es.uji.ei102718cln.TooPots.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei102718cln.TooPots.model.*;



public final class ReservationRowMapper implements RowMapper<Reservation> {
    public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setActivityID(rs.getInt("activityId"));
        reservation.setActivityDate(rs.getObject("activityDate", LocalDate.class));
        reservation.setBookingDate(rs.getObject("bookingDate", LocalDate.class));
        reservation.setCustomerID(rs.getString("customerID"));
        reservation.setNumberOfPersons(rs.getInt("numberOfPersons"));
        reservation.setPriceByPerson(rs.getFloat("priceByPerson"));
        reservation.setReservationID(rs.getInt("reservationID"));
        reservation.setTotalPrice(rs.getFloat("totalPrice"));
        reservation.setState(rs.getString("state"));
        reservation.setMsg(rs.getString("msg"));
        return reservation;
    }
}