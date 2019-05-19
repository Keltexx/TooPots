package es.uji.ei102718cln.TooPots.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.ei102718cln.TooPots.model.Instructor;
import es.uji.ei102718cln.TooPots.model.Reservation;

public class ReservationValidator implements Validator{
	@Override
	  public boolean supports(Class<?> cls) {
		  return Reservation.class.equals(cls);

	   }
	 
	  @Override
	  public void validate(Object obj, Errors errors) {
		  Reservation reservation = (Reservation)obj;
		 if (reservation.getActivityDate().equals(""))
		       errors.rejectValue("name", "obligatorio",
		                          "Es necesario introducir un valor");
		 if (reservation.getBookingDate().equals(""))
		       errors.rejectValue("place", "obligatorio",
		                          "Es necesario introducir un valor");
		 if (reservation.getNumberOfPersons()==0)
		       errors.rejectValue("instructorId", "obligatorio",
		                          "Es necesario introducir un valor");
		 if (reservation.getPriceByPerson()<0)
		       errors.rejectValue("instructorId", "obligatorio",
		                          "Es necesario introducir un valor positivo");
	

		 


	   }

}