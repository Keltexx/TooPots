package es.uji.ei102718cln.TooPots.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import es.uji.ei102718cln.TooPots.model.Customer;

public class CustomerValidator implements Validator{
	@Override
	  public boolean supports(Class<?> cls) {
		  return Customer.class.equals(cls);

	   }
	 
	  @Override
	  public void validate(Object obj, Errors errors) {
		 Customer customer = (Customer)obj;
		 if (customer.getName().trim().equals(""))
		       errors.rejectValue("name", "obligatorio",
		                          "Es necesario introducir un valor");
		 if (customer.getGender().trim().equals(""))
		       errors.rejectValue("gender", "obligatorio",
		                          "Es necesario introducir un valor");
		 if (customer.getEmail().trim().equals(""))
		       errors.rejectValue("email", "obligatorio",
		                          "Es necesario introducir un valor");
		 if (customer.getNif().trim().equals(""))
		       errors.rejectValue("nif", "obligatorio",
		                          "Es necesario introducir un valor");
		 if (customer.getBirthDate().toString().trim().equals(""))
		       errors.rejectValue("birth", "obligatorio",
		                          "Es necesario introducir un valor");

		 


	   }

}
