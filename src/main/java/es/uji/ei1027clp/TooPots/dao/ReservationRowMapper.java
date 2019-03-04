package es.uji.ei1027clp.TooPots.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import es.uji.ei1027clp.TooPots.model.*;
import org.springframework.jdbc.core.RowMapper;



public final class ReservationRowMapper implements RowMapper<Reservation> {
    public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setActivityID(rs.getString("activityID"));
        reservation.setActivityDate(rs.getDate("activityDate"));
        reservation.setBookingDate(rs.getDate("bookingDate"));
        reservation.setCustomerID(rs.getString("customerID"));
        reservation.setNumberOfPersons(rs.getInt("numberOfPersons"));
        reservation.setPriceByPerson(rs.getInt("priceByPerson"));
        reservation.setReservationID(rs.getString("reservationID"));
        reservation.setTotalPrice(rs.getInt("totalPrice"));
        return reservation;
    }
}