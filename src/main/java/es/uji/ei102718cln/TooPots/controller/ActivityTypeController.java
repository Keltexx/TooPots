package es.uji.ei102718cln.TooPots.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei102718cln.TooPots.dao.ActivityTypeDao;
import es.uji.ei102718cln.TooPots.model.ActivityType;


@Controller
@RequestMapping("/activityType")
public class ActivityTypeController {
	
	   private ActivityTypeDao activityTypeDao;

	   @Autowired
	   public void setActivityTypeDao(ActivityTypeDao activityTypeDao) { 
	       this.activityTypeDao=activityTypeDao;
	   }

	   @RequestMapping("/list")
	   public String listActivityTypes(Model model) {
	      model.addAttribute("activityTypes", activityTypeDao.getActivityTypes());
	      return "activityType/list"; 
	   }
	   
		
		@RequestMapping(value="/add") 
	   public String addActivityType(Model model) {
	       model.addAttribute("activityType", new ActivityType());
	       return "activityType/add";
	   }
		@RequestMapping(value="/add", method=RequestMethod.POST) 
		public String processAddSubmit(@ModelAttribute("activityType") ActivityType activityType,
		                                BindingResult bindingResult) {  
		     if (bindingResult.hasErrors()) 
		            return "activityType/add";
		     activityTypeDao.addActivityType(activityType);
		     return "redirect:list"; 
		     
		 }
		@RequestMapping(value="/update/{nameType}", method = RequestMethod.GET) 
		public String editActivityType(Model model, @PathVariable String nameType) { 
			model.addAttribute("activityType", activityTypeDao.getActivityType(nameType));
			return "activityType/update"; 
		}
		
		@RequestMapping(value="/update/{nameType}", method = RequestMethod.POST) 
		public String processUpdateSubmit(@PathVariable String nameType, 
	                            @ModelAttribute("activityType") ActivityType activityType, 
	                            BindingResult bindingResult) {
			 if (bindingResult.hasErrors()) 
				 return "activityType/update";
			
			 activityTypeDao.updateActivityType(activityType);
			 return "redirect:../list"; 
		}
		@RequestMapping(value="/delete/{name}")
		public String processDelete(@PathVariable String nameType) {
	           activityTypeDao.deleteActivityType(nameType);
	           return "redirect:../list"; 
		}
		
		
}
