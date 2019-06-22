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
import es.uji.ei102718cln.TooPots.dao.CanOfferDao;
import es.uji.ei102718cln.TooPots.model.CanOffer;
import es.uji.ei102718cln.TooPots.model.Login;

@Controller
@RequestMapping("/canOffer")
public class CanOfferController {

	private CanOfferDao canOfferDao;
	private ActivityTypeDao activityTypeDao;

	@Autowired
	public void setActivityTypeDao(ActivityTypeDao activityTypeDao) {
		this.activityTypeDao = activityTypeDao;
	}

	@Autowired
	public void setCanOfferDao(CanOfferDao canOfferDao) {
		this.canOfferDao = canOfferDao;
	}
		
	@RequestMapping("/list")
	public String listCanOffers(HttpSession session, Model model) {
		Login login = (Login) session.getAttribute("usuario");

		if (login == null) {
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "canOffer/list");
			return "login";
		}

		if (!login.getRol().equals("admin")) {
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "canOffer/list");
			return "login";
		}
		model.addAttribute("canoffers", canOfferDao.getCanOffers());
		return "canOffer/list";
	}

	@RequestMapping(value = "/add")
	public String addCanOffer(Model model) {
		model.addAttribute("canOffer", new CanOffer());
		return "canOffer/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("canOffer") CanOffer canOffer, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "canOffer/add";
		canOfferDao.addCanOffer(canOffer);
		return "redirect:/request/list";

	}

	@RequestMapping(value = "/add/{instructorId}")
	public String addCanOfferFix(Model model, @PathVariable String instructorId, Model model2) {

		model.addAttribute("canOffer", new CanOffer(instructorId));
		model2.addAttribute("activityTypes", activityTypeDao.getActivityTypes());
		return "canOffer/add";
	}

	@RequestMapping(value = "/add/{instructorId}", method = RequestMethod.POST)
	public String processAddFixSubmit(@PathVariable String instructorId, @ModelAttribute("canOffer") CanOffer canOffer,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "canOffer/add";
		canOfferDao.addCanOffer(canOffer);
		return "redirect:request/list";

	}

	@RequestMapping(value = "/update/{canOfferId}", method = RequestMethod.GET)
	public String editCanOffer(Model model, @PathVariable int canOfferId) {
		model.addAttribute("canOffer", canOfferDao.getCanOffer(canOfferId));
		return "canOffer/update";
	}

	@RequestMapping(value = "/update/{canOfferId}", method = RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable int canOfferId, @ModelAttribute("canOffer") CanOffer canOffer,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "canOffer/update";

		canOfferDao.updateCanOffer(canOffer);
		return "redirect:../list";
	}

	@RequestMapping(value = "/delete/{canOfferId}")
	public String processDelete(@PathVariable int canOfferId) {
		canOfferDao.deleteCanOffer(canOfferId);
		return "redirect:../list";
	}

}
