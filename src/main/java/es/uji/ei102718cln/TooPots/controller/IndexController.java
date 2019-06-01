package es.uji.ei102718cln.TooPots.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.uji.ei102718cln.TooPots.dao.ActivityDao;

@Controller
public class IndexController {
	private ActivityDao activityDao;
	
	@Autowired
	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao=activityDao;
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("activities", activityDao.getActivities());
		return "index";
	}
	
	@RequestMapping("/customer/home")
	public String index_customer(Model model) {
		model.addAttribute("activities", activityDao.getActivities());
		return "customer/home";
	}
	
	@RequestMapping("/instructor/home")
	public String index_instructor(Model model) {
		model.addAttribute("activities", activityDao.getActivities());
		return "instructor/home";
	}
	
	@RequestMapping("/admin/home")
	public String index_admin(Model model) {
		model.addAttribute("activities", activityDao.getActivities());
		return "admin/home";
	}
	
}
