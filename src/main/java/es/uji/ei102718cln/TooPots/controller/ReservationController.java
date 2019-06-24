package es.uji.ei102718cln.TooPots.controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.uji.ei102718cln.TooPots.dao.ActivityDao;
import es.uji.ei102718cln.TooPots.dao.ReservationDao;
import es.uji.ei102718cln.TooPots.model.Activity;
import es.uji.ei102718cln.TooPots.model.Login;
import es.uji.ei102718cln.TooPots.model.Reservation;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

	private ReservationDao reservationDao;
	private ActivityDao activityDao;

	@Autowired
	public void setReservationDao(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}

	@Autowired
	public void setActividadDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}

//	@RequestMapping("/list")
//	public String listReservation(Model model) {
//		model.addAttribute("reservations", reservationDao.getReservations());
//		return "reservation/list";
//
//	}

	@RequestMapping(value = "/listReservation/{activityId}")
	public String listReservationActivity(Model model, @PathVariable String activityId, HttpSession session) {
		Login login = (Login) session.getAttribute("usuario");

		if (login == null) {
			model.addAttribute("usuario", new Login());
			return "login";
		}

		if (!login.getRol().equals("instructor")) {
			session.invalidate();
			model.addAttribute("usuario", new Login());

			return "login";
		}
		model.addAttribute("reservationsInstructor", reservationDao.getReservationsActivity(activityId));
		return "reservation/listReservation";

	}

	@RequestMapping("/listCustomer")
	public String listReservationCustomer(HttpSession session, Model model) {
		Login login = (Login) session.getAttribute("usuario");

		if (login == null) {
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "reservation/listCustomer");
			return "login";
		}

		if (!login.getRol().equals("customer")) {
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "reservation/listCustomer");
			return "login";
		}

		model.addAttribute("reservationsCustomer", reservationDao.getReservationsCustomer(login.getUsuario()));
		return "reservation/listCustomer";

	}

	@RequestMapping("/listReservationAdmin/{nif}")
	public String listReservationAdmin(HttpSession session, Model model, @PathVariable String nif) {
		Login login = (Login) session.getAttribute("usuario");

		if (login == null) {
			model.addAttribute("usuario", new Login());
			return "login";
		}

		if (!login.getRol().equals("admin")) {
			session.invalidate();
			model.addAttribute("usuario", new Login());

			return "login";
		}

		model.addAttribute("reservationsCustomerAdmin", reservationDao.getReservationsCustomer(nif));
		return "reservation/listReservationAdmin";

	}

	@RequestMapping(value = "/update/{reservationID}", method = RequestMethod.GET)
	public String editReservation(Model model, @PathVariable String reservationID) {
		model.addAttribute("reservation", reservationDao.getReservation(reservationID));
		return "reservation/update";
	}

	@RequestMapping(value = "/update/{reservationID}", method = RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String reservationID,
			@ModelAttribute("reservation") Reservation reservation, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "reservation/update";

		reservationDao.updateReservation(reservation);
		return "redirect:../list";
	}

	@RequestMapping(value = "/delete/{reservationID}")
	public String processDelete(@PathVariable String reservationID, HttpSession session, Model model) {
		Login login = (Login) session.getAttribute("usuario");

		if (login == null) {
			model.addAttribute("usuario", new Login());
			return "login";
		}

		if (!login.getRol().equals("customer")) {
			session.invalidate();
			model.addAttribute("usuario", new Login());

			return "login";
		}
		Reservation reservation = reservationDao.getReservation(reservationID);
		if (reservation.getState().equals("payed"))
			return "redirect:../listCustomer";
		reservationDao.deleteReservation(reservationID);
		System.out.println("Reserva anulada");
		return "redirect:../listCustomer";
	}

	@RequestMapping("/accept/{reservationID}")
	public String processAccept(Model model, @PathVariable String reservationID, HttpSession session) {
		Login login = (Login) session.getAttribute("usuario");

		if (login == null) {
			model.addAttribute("usuario", new Login());
			return "login";
		}

		if (!login.getRol().equals("instructor")) {
			session.invalidate();
			model.addAttribute("usuario", new Login());

			return "login";
		}
		Reservation reservation = reservationDao.getReservation(reservationID);
		model.addAttribute("reservationsInstructor",
				reservationDao.getReservationsActivity("" + reservation.getActivityID()));

		if (!reservation.getState().equals("confirmed")) {
			return "reservation/listReservation";

		}
		reservationDao.updateStateAccepted(reservationID);
		System.out.println("Reserva aceptada, debe pagarla antes de la realización de la actividad");
		return "reservation/listReservation";
	}

	@RequestMapping(value = "/pay/{reservationID}")
	public String processPay(@PathVariable String reservationID) {

		Reservation reservation = reservationDao.getReservation(reservationID);
		long totalDias = ChronoUnit.DAYS.between(LocalDate.now(), reservation.getActivityDate());
		System.out.println(totalDias);
		if (totalDias > 10)
			return "redirect:/reservation/listCustomer";
		if (!reservation.getState().equals("accepted")) {
			return "redirect:/reservation/listCustomer";
		}
		reservationDao.updateStatePay(reservationID);
		System.out.println("Simulación de que la PPS ha efectuado el pago cuando faltan 10 días para la actividad");
		;
		return "redirect:../listCustomer";
	}

	@RequestMapping(value = "/add/{activityId}")
	public String addReservationActivity(HttpSession session, Model model, @PathVariable String activityId) {
		Login login = (Login) session.getAttribute("usuario");

		if (login == null) {
			model.addAttribute("usuario", new Login());

			return "login";
		}

		if (!login.getRol().equals("customer")) {
			session.invalidate();
			model.addAttribute("usuario", new Login());

			return "login";
		}

		Activity activity = activityDao.getActivity(activityId);
		int max = activity.getNumberOfPeople();
		int reservas = reservationDao.getPeople("" + activity.getActivityId());
		int maxValue = max - reservas;
		model.addAttribute("reservation",
				new Reservation(activity.getSchedule(), activity.getPriceByPerson(), activity.getActivityId()));
		model.addAttribute("maxValue", maxValue);
		return "/reservation/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddReservationActivitySubmit(Model model, HttpSession session,
			@ModelAttribute("reservation") Reservation reservation,
			@RequestParam(name = "numberOfPersons") int numberOfPersons, BindingResult bindingResult) {
		if (session.getAttribute("usuario") == null) {
			model.addAttribute("usuario", new Login());
			return "login";
		}

		reservation.setBookingDate(LocalDate.now());
		reservation.setNumberOfPersons(numberOfPersons);
		reservation.setTotalPrice(numberOfPersons * reservation.getPriceByPerson());
		Login usuario = (Login) session.getAttribute("usuario");
		reservation.setCustomerID(usuario.getUsuario());
		reservation.setState("pending");

//			ReservationValidator reservationValidator = new ReservationValidator();
//			reservationValidator.validate(reservation, bindingResult);

		if (bindingResult.hasErrors())
			return "reservation/add";

		reservationDao.addReservation(reservation);
		return "redirect:../reservation/listCustomer";
	}

	@RequestMapping(value = "/enviaMsg/{reservationID}", method = RequestMethod.GET)
	public String enviaMsg(Model model, @PathVariable String reservationID) {
//		Reservation reservation = reservationDao.getReservation(reservationID);
//		if (!reservation.getState().equals("confirmed")) {
//			model.addAttribute("reservationsInstructor", reservationDao.getReservationsActivity(""+reservation.getActivityID()));
//			return "/activity/listActivity";
//
//		}
		model.addAttribute("reservation", reservationDao.getReservation(reservationID));
		return "reservation/enviaMsg";
	}

	@RequestMapping(value = "/enviaMsg/{reservationID}", method = RequestMethod.POST)
	public String processEnviaMsg(@PathVariable String reservationID,
			@ModelAttribute("reservation") Reservation reservation, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "reservation/enviaMsg";



		reservationDao.enviaMsg(reservation);
		// reservationDao.updateStateAccepted(reservationID);
		System.out.println(reservation.getMsg());
		return "reservation/listReservation";
	}

}