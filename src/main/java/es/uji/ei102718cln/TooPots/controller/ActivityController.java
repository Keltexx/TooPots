package es.uji.ei102718cln.TooPots.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.uji.ei102718cln.TooPots.dao.ActivityDao;




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

}
