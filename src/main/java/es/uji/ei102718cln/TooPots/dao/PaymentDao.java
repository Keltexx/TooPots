package es.uji.ei102718cln.TooPots.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei102718cln.TooPots.model.CanOffer;
import es.uji.ei102718cln.TooPots.model.Login;
import es.uji.ei102718cln.TooPots.model.Payment;

@Repository
public class PaymentDao {
	private JdbcTemplate jdbcTemplate;

	// Obté el jdbcTemplate a partir del Data Source
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void addPayment(Payment payment) {
		jdbcTemplate.update("INSERT INTO payment(reservationid,bankaccount,customerid) VALUES(?, ?, ?)",
				payment.getReservationId(), payment.getBankAccount(), payment.getCustomerId());
	}

	public Payment getPayment(int paymentid) {
		try {
			return jdbcTemplate.queryForObject("SELECT * from Payment WHERE paymentid=?", new PaymentRowMapper(),
					paymentid);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<Payment> getPayments() {
		try {
			return jdbcTemplate.query("SELECT * from payment", new PaymentRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<Payment>();
		}
	}
}
