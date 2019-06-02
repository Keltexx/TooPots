package es.uji.ei102718cln.TooPots.controller;

import javax.servlet.http.HttpSession;

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
import es.uji.ei102718cln.TooPots.model.Login;


@Controller
@RequestMapping("/activityType")
public class ActivityTypeController {
	
	   private ActivityTypeDao activityTypeDao;

	   @Autowired
	   public void setActivityTypeDao(ActivityTypeDao activityTypeDao) { 
	       this.activityTypeDao=activityTypeDao;
	   }

	   @RequestMapping("/list")
	   public String listActivityTypes(HttpSession session, Model model) {
		   Login login = (Login) session.getAttribute("usuario");
			
			if (login == null) {
				model.addAttribute("usuario", new Login());
				session.setAttribute("nextUrl", "activityType/list");
				return "login";
			}
			
			if(!login.getRol().equals("instructor")) {
				model.addAttribute("usuario", new Login());
				session.setAttribute("nextUrl", "activityType/list");
				return "login";
			}
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
		@RequestMapping(value="/update/{activityTypeId}", method = RequestMethod.GET) 
		public String editActivityType(Model model, @PathVariable String activityTypeId) { 
			model.addAttribute("activityType", activityTypeDao.getActivityType(activityTypeId));
			return "activityType/update"; 
		}
		
		@RequestMapping(value="/update/{activityTypeId}", method = RequestMethod.POST) 
		public String processUpdateSubmit(@PathVariable String activityTypeId, 
	                            @ModelAttribute("activityType") ActivityType activityType, 
	                            BindingResult bindingResult) {
			 if (bindingResult.hasErrors()) 
				 return "activityType/update";
			
			 activityTypeDao.updateActivityType(activityType);
			 return "redirect:../list"; 
		}
		@RequestMapping(value="/delete/{activityTypeId}")
		public String processDelete(@PathVariable String activityTypeId) {
	           activityTypeDao.deleteActivityType(activityTypeId);
	           return "redirect:../list"; 
		}
		
		
}
