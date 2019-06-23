package es.uji.ei102718cln.TooPots.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import es.uji.ei102718cln.TooPots.model.Payment;


public final class PaymentRowMapper implements RowMapper<Payment>{
	public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Payment payment = new Payment();
        payment.setPaymentId(rs.getInt("paymentId"));
        payment.setReservationId(rs.getInt("reservationId"));
        payment.setBankAccount(rs.getString("reservationId"));
        payment.setCustomerId(rs.getString("customerId"));
        
        return payment;
	}
}
