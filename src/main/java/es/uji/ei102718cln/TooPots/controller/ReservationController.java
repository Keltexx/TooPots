package es.uji.ei102718cln.TooPots.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei102718cln.TooPots.dao.ReservationDao;
import es.uji.ei102718cln.TooPots.model.Reservation;



@Controller    
@RequestMapping("/reservation")
public class ReservationController {
	
	   private ReservationDao reservationDao;

	   @Autowired
	   public void setReservationDao(ReservationDao reservationDao) { 
	       this.reservationDao=reservationDao;
	   }

	   @RequestMapping("/list")
	   public String listReservation(Model model) {
	      model.addAttribute("reservations", reservationDao.getReservations());
	      return "reservation/list";
	   
	   }
	   
		
		@RequestMapping(value="/add") 
	   public String addReservation(Model model) {
	       model.addAttribute("reservation", new Reservation());
	       return "reservation/add";
	   }
		@RequestMapping(value="/add", method=RequestMethod.POST) 
		public String processAddSubmit(@ModelAttribute("reservation") Reservation reservation,
		                                BindingResult bindingResult) {  
		     if (bindingResult.hasErrors()) 
		            return "reservation/add";
		     reservationDao.addReservation(reservation);
		     return "redirect:list"; 
		     
		 }
		@RequestMapping(value="/update/{reservationID}", method = RequestMethod.GET) 
		public String editReservation(Model model, @PathVariable String reservationid) { 
			model.addAttribute("resevation", reservationDao.getReservation(reservationid));
			return "reservation/update"; 
		}
		
		@RequestMapping(value="/update/{reservationID}", method = RequestMethod.POST) 
		public String processUpdateSubmit(@PathVariable String reservationid, 
	                            @ModelAttribute("reservation") Reservation reservation, 
	                            BindingResult bindingResult) {
			 if (bindingResult.hasErrors()) 
				 return "reservation/update";
			
			 reservationDao.updateReservation(reservation);
			 return "redirect:../list"; 
		}
		@RequestMapping(value="/delete/{reservationID}")
		public String processDelete(@PathVariable String reservationid) {
	           reservationDao.deleteReservation(reservationid);
	           return "redirect:../list"; 
		}
		
		

}