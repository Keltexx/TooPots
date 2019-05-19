package es.uji.ei102718cln.TooPots.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.ei102718cln.TooPots.model.Customer;
import es.uji.ei102718cln.TooPots.model.Instructor;

public class InstructorValidator implements Validator{
	@Override
	  public boolean supports(Class<?> cls) {
		  return Instructor.class.equals(cls);

	   }
	 
	  @Override
	  public void validate(Object obj, Errors errors) {
		 Instructor instructor = (Instructor)obj;
		 if (instructor.getName().trim().equals(""))
		       errors.rejectValue("name", "obligatorio",
		                          "Es necesario introducir un valor");
		 if (instructor.getBankAccount().trim().equals(""))
		       errors.rejectValue("bankAccount", "obligatorio",
		                          "Es necesario introducir un valor");
		 if (instructor.getEmail().trim().equals(""))
		       errors.rejectValue("email", "obligatorio",
		                          "Es necesario introducir un valor");
		 if (instructor.getNif().trim().equals(""))
		       errors.rejectValue("nif", "obligatorio",
		                          "Es necesario introducir un valor");
	

		 


	   }

}
