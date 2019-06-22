package es.uji.ei102718cln.TooPots.controller;



import org.springframework.validation.Validator;
import org.springframework.validation.Errors;


import es.uji.ei102718cln.TooPots.model.Activity;

public class ActivityValidator implements Validator{
	@Override
	  public boolean supports(Class<?> cls) {
		  return Activity.class.equals(cls);

	   }
	 
	  @Override
	  public void validate(Object obj, Errors errors) {
		 Activity activity = (Activity)obj;
		 if (activity.getName().trim().equals(""))
		       errors.rejectValue("name", "obligatorio",
		                          "Es necesario introducir un valor");
		 if (activity.getPlace().trim().equals(""))
		       errors.rejectValue("place", "obligatorio",
		                          "Es necesario introducir un valor");
		 if (activity.getInstructorId().trim().equals(""))
		       errors.rejectValue("instructorId", "obligatorio",
		                          "Es necesario introducir un valor");
		 if (activity.getDescription().trim().equals(""))
		       errors.rejectValue("description", "obligatorio",
		                          "Es necesario introducir un valor");
		

		 if(activity.getPriceByPerson()<=0)
			 errors.rejectValue("priceByPerson", "menor o igual que 0", "Es necesario introducir un valor mayor que 0");
		 if(activity.getNumberOfPeople()<=0)
			 errors.rejectValue("numberOfPeople", "menor o igual que 0", "Es necesario introducir un valor mayor que 0");
		 


	   }
}
