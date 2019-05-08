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
	       model.addAttribute("activity", new Activity());
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
		@RequestMapping(value="/update/{activityid}", method = RequestMethod.GET) 
		public String editActivity(Model model, @PathVariable String activityid) { 
			model.addAttribute("activity", activityDao.getActivity(activityid));
			return "activity/update"; 
		}
		
		@RequestMapping(value="/update/{activityid}", method = RequestMethod.POST) 
		public String processUpdateSubmit(@PathVariable String activityid, 
	                            @ModelAttribute("activity") Activity activity, 
	                            BindingResult bindingResult) {
			 if (bindingResult.hasErrors()) 
				 return "activity/update";
			
			 activityDao.updateActivity(activity);
			 return "redirect:../list"; 
		}
		@RequestMapping(value="/delete/{activityid}")
		public String processDelete(@PathVariable String activityid) {
	           activityDao.deleteActivity(activityid);
	           return "redirect:../list"; 
		}
		
		

}
