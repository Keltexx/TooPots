package es.uji.ei102718cln.TooPots.controller;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
	       this.reservationDao=reservationDao;
	   }

		@Autowired
		public void setActividadDao(ActivityDao activityDao) {
			this.activityDao = activityDao;
		}
		
	   @RequestMapping("/list")
	   public String listReservation(Model model) {
	      model.addAttribute("reservations", reservationDao.getReservations());
	      return "reservation/list";
	   
	   }
	   
	   @RequestMapping("/listCustomer")
	   public String listReservationCustomer(HttpSession session, Model model) {
		   Login usuario = (Login)session.getAttribute("usuario");
	      model.addAttribute("reservationsCustomer", reservationDao.getReservationsCustomer(usuario.getUsuario()));
	      return "reservation/listCustomer";
	   
	   }
	   
		
		@RequestMapping(value="/update/{reservationID}", method = RequestMethod.GET) 
		public String editReservation(Model model, @PathVariable String reservationID) { 
			model.addAttribute("reservation", reservationDao.getReservation(reservationID));
			return "reservation/update"; 
		}
		
		@RequestMapping(value="/update/{reservationID}", method = RequestMethod.POST) 
		public String processUpdateSubmit(@PathVariable String reservationID, 
	                            @ModelAttribute("reservation") Reservation reservation, 
	                            BindingResult bindingResult) {
			 if (bindingResult.hasErrors()) 
				 return "reservation/update";
			
			 reservationDao.updateReservation(reservation);
			 return "redirect:../list"; 
		}
		
		@RequestMapping(value="/delete/{reservationID}")
		public String processDelete(@PathVariable String reservationID) {
	           reservationDao.deleteReservation(reservationID);
	           return "redirect:../list"; 
		}
		

		@RequestMapping(value = "/add/{activityId}" )
		public String addReservationActivity(HttpSession session,Model model, @PathVariable String activityId) {
			Login login = (Login) session.getAttribute("usuario");
			
			if (login == null) {
				model.addAttribute("usuario", new Login());
				session.setAttribute("nextUrl", "activity/gallery_instructor");
				return "login";
			}
			
			if(!login.getRol().equals("customer")) {
				model.addAttribute("usuario", new Login());
				session.setAttribute("nextUrl", "activity/gallery_instructor");
				return "login";
			}
			
			Activity activity = activityDao.getActivity(activityId);
			model.addAttribute("reservation", new Reservation(activity.getSchedule(),activity.getPriceByPerson(), activity.getActivityId()));
			return "/reservation/add";
		}
		
		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public String processAddReservationActivitySubmit(Model model,HttpSession session, @ModelAttribute("reservation") Reservation reservation, @RequestParam(name = "numberOfPersons") int numberOfPersons,
				BindingResult bindingResult) {
			if (session.getAttribute("usuario") == null) {
				model.addAttribute("usuario", new Login());
				session.setAttribute("nextUrl", "activity/gallery");
				return "login";
			}
			if (bindingResult.hasErrors())
				return "reservation/add";
			
			reservation.setBookingDate(LocalDate.now());
			reservation.setNumberOfPersons(numberOfPersons);
			reservation.setTotalPrice(numberOfPersons * reservation.getPriceByPerson());
			Login usuario = (Login) session.getAttribute("usuario");
			reservation.setCustomerID(usuario.getUsuario());
			//reserva.setState("pendiente");
			reservationDao.addReservation(reservation);
			return "redirect:../reservation/list";
		}
		

}