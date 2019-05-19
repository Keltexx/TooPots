package es.uji.ei102718cln.TooPots.controller;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.ei102718cln.TooPots.model.ActivityType;

public class ActivityTypeValidator implements Validator{
	@Override
	  public boolean supports(Class<?> cls) {
		  return ActivityType.class.equals(cls);

	   }
	 
	  @Override
	  public void validate(Object obj, Errors errors) {
		 ActivityType activityType = (ActivityType)obj;
		 if (activityType.getNameType().trim().equals(""))
		       errors.rejectValue("name", "obligatorio",
		                          "Es necesario introducir un valor");
		 if (activityType.getDescription().trim().equals(""))
		       errors.rejectValue("description", "obligatorio",
		                          "Es necesario introducir un valor");
		 if (activityType.getRiskLevel().trim().equals(""))
		       errors.rejectValue("riskLevel", "obligatorio",
		                          "Es necesario introducir un valor");

		 


	   }

}
