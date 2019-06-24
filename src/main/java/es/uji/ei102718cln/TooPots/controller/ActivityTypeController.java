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
		this.activityTypeDao = activityTypeDao;
	}

	@RequestMapping("/list")
	public String listActivityTypes(HttpSession session, Model model) {
		Login login = (Login) session.getAttribute("usuario");

		if (login == null) {
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "activityType/list");
			return "login";
		}

		if (!login.getRol().equals("admin")) {
			if(login.getRol().equals("instructor"))
				return "redirect:/instructor/home";
			if(login.getRol().equals("customer"))
				return "redirect:/customer/home";
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "activityType/list");
			return "login";
		}
		model.addAttribute("activityTypes", activityTypeDao.getActivityTypes());
		return "activityType/list";
	}

	@RequestMapping(value = "/add")
	public String addActivityType(Model model,HttpSession session) {
		Login login = (Login) session.getAttribute("usuario");

		if (login == null) {
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "activityType/add");
			return "login";
		}

		if (!login.getRol().equals("admin")) {
			if(login.getRol().equals("instructor"))
				return "redirect:/instructor/home";
			if(login.getRol().equals("customer"))
				return "redirect:/customer/home";
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "activityType/add");
			return "login";
		}
		model.addAttribute("activityType", new ActivityType());
		return "activityType/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("activityType") ActivityType activityType,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "activityType/add";
		activityTypeDao.addActivityType(activityType);
		return "redirect:list";

	}

	@RequestMapping(value = "/update/{nameType}", method = RequestMethod.GET)
	public String editActivityType(Model model,HttpSession session, @PathVariable String nameType) {
		Login login = (Login) session.getAttribute("usuario");

		if (login == null) {
			model.addAttribute("usuario", new Login());
			return "login";
		}

		if (!login.getRol().equals("admin")) {
			if(login.getRol().equals("instructor"))
				return "redirect:/instructor/home";
			if(login.getRol().equals("customer"))
				return "redirect:/customer/home";
			session.invalidate();
			model.addAttribute("usuario", new Login());
			return "login";
		}
		model.addAttribute("activityType", activityTypeDao.getActivityType(nameType));
		return "activityType/update";
	}

	@RequestMapping(value = "/update/{nameType}", method = RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String nameType,
			@ModelAttribute("activityType") ActivityType activityType, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "activityType/update";

		activityTypeDao.updateActivityType(activityType);
		return "redirect:../list";
	}

	@RequestMapping(value = "/delete/{nameType}")
	public String processDelete(Model model, HttpSession session, @PathVariable String nameType) {
		Login login = (Login) session.getAttribute("usuario");

		if (login == null) {
			model.addAttribute("usuario", new Login());
			return "login";
		}

		if (!login.getRol().equals("admin")) {
			if(login.getRol().equals("instructor"))
				return "redirect:/instructor/home";
			if(login.getRol().equals("customer"))
				return "redirect:/customer/home";
			session.invalidate();
			model.addAttribute("usuario", new Login());
			return "login";
		}
		activityTypeDao.deleteActivityType(nameType);
		return "redirect:../list";
	}

}
