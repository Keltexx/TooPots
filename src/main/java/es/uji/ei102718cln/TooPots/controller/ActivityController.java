package es.uji.ei102718cln.TooPots.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei102718cln.TooPots.dao.ActivityDao;
import es.uji.ei102718cln.TooPots.model.Activity;
import es.uji.ei102718cln.TooPots.model.Customer;




@Controller    
@RequestMapping("/activity")
public class ActivityController {
	
	   private ActivityDao activityDao;

	   @Autowired
	   public void setActivityDao(ActivityDao activityDao) { 
	       this.activityDao=activityDao;
	   }

	   @RequestMapping("/list")
	   public String listActivities(Model model) {
	      model.addAttribute("activities", activityDao.getActivities());
	      return "activity/list";
	   
	   }
	   
		
		@RequestMapping(value="/add") 
	   public String addActivity(Model model) {
	       model.addAttribute("activity", new Customer());
	       return "activity/add";
	   }
		@RequestMapping(value="/add", method=RequestMethod.POST) 
		public String processAddSubmit(@ModelAttribute("activity") Activity activity,
		                                BindingResult bindingResult) {  
		     if (bindingResult.hasErrors()) 
		            return "activity/add";
		     activityDao.addActivity(activity);
		     return "redirect:list"; 
		     
		 }
		@RequestMapping(value="/update/{name}", method = RequestMethod.GET) 
		public String editActivity(Model model, @PathVariable String name) { 
			model.addAttribute("activity", activityDao.getActivity(name));
			return "activity/update"; 
		}
		
		@RequestMapping(value="/update/{name}", method = RequestMethod.POST) 
		public String processUpdateSubmit(@PathVariable String name, 
	                            @ModelAttribute("activity") Activity activity, 
	                            BindingResult bindingResult) {
			 if (bindingResult.hasErrors()) 
				 return "activity/update";
			
			 activityDao.updateActivity(activity);
			 return "redirect:../list"; 
		}
		@RequestMapping(value="/delete/{name}")
		public String processDelete(@PathVariable String name) {
	           activityDao.deleteActivity(name);
	           return "redirect:../list"; 
		}
		
		

}
