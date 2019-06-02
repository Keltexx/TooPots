package es.uji.ei102718cln.TooPots.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.uji.ei102718cln.TooPots.dao.ActivityDao;
import es.uji.ei102718cln.TooPots.model.Login;

@Controller
public class IndexController {
	private ActivityDao activityDao;

	@Autowired
	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("activities", activityDao.getActivities());
		return "index";
	}

	@RequestMapping("/customer/home")
	public String index_customer(HttpSession session, Model model) {
		Login login = (Login) session.getAttribute("usuario");

		if (login == null) {
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "customer/home");
			return "login";
		}

		if (!login.getRol().equals("customer")) {
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "customer/home");
			return "login";
		}
		model.addAttribute("activities", activityDao.getActivities());
		return "customer/home";
	}

	@RequestMapping("/instructor/home")
	public String index_instructor(HttpSession session, Model model) {
		Login login = (Login) session.getAttribute("usuario");

		if (login == null) {
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "instructor/home");
			return "login";
		}

		if (!login.getRol().equals("instructor")) {
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "instructor/home");
			return "login";
		}
		model.addAttribute("activities", activityDao.getActivities());
		return "instructor/home";
	}

	@RequestMapping("/admin/home")
	public String index_admin(HttpSession session, Model model) {
		Login login = (Login) session.getAttribute("usuario");

		if (login == null) {
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "admin/home");
			return "login";
		}

		if (!login.getRol().equals("admin")) {
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "admin/home");
			return "login";
		}
		model.addAttribute("activities", activityDao.getActivities());
		return "admin/home";
	}

}
