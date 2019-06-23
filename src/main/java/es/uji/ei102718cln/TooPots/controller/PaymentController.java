package es.uji.ei102718cln.TooPots.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei102718cln.TooPots.dao.PaymentDao;
import es.uji.ei102718cln.TooPots.dao.ReservationDao;
import es.uji.ei102718cln.TooPots.model.CanOffer;
import es.uji.ei102718cln.TooPots.model.Login;
import es.uji.ei102718cln.TooPots.model.Payment;
import es.uji.ei102718cln.TooPots.model.Reservation;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	private PaymentDao paymentDao;
	private ReservationDao reservationDao;

	@Autowired
	public void setReservationDao(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}

	@Autowired
	public void setPaymentDao(PaymentDao paymentDao) {
		this.paymentDao = paymentDao;
	}

	@RequestMapping("/list")
	public String list(HttpSession session, Model model) {
		Login login = (Login) session.getAttribute("usuario");

		if (login == null) {
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "customer/home");
			return "login";
		}

		if (!login.getRol().equals("customer")) {
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "payment/list");
			return "login";
		}

		model.addAttribute("instructors", paymentDao.getPayments());
		return "payment/list";

	}
	
	@RequestMapping(value = "/add")
	public String addPayment2(Model model) {
		model.addAttribute("payment", new Payment());
		return "payment/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddSubmit2(@ModelAttribute("payment") Payment payment, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "payment/add";
		reservationDao.updateStateConfirmed(""+payment.getReservationId());
		paymentDao.addPayment(payment);
		return "redirect:/reservation/listCustomer";

	}

	@RequestMapping(value = "/add/{reservationId}")
	public String addPayment(HttpSession session, Model model, @PathVariable int reservationId) {
		Login login = (Login) session.getAttribute("usuario");

		if (login == null) {
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "payment/add");
			return "login";
		}

		if (!login.getRol().equals("customer")) {
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "payment/add");
			return "login";
		}
		
		Reservation reservation = reservationDao.getReservation(""+reservationId);
		if(reservation.getState().equals("confirmed")) {
			return "redirect:/reservation/listCustomer";
		}
		

		Payment payment = new Payment();
		payment.setCustomerId(login.getUsuario());
		payment.setReservationId(reservationId);

		model.addAttribute("payment", payment);
		return "payment/add";
	}

	@RequestMapping(value = "/add/{reservationId}", method = RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("canOffer") Payment payment, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "payment/add";
		
		reservationDao.updateStateConfirmed(""+payment.getReservationId());
		paymentDao.addPayment(payment);
		return "redirect:/reservation/listCustomer";

	}

}
