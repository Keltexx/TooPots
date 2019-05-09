package es.uji.ei102718cln.TooPots.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei102718cln.TooPots.dao.InstructorDao;
import es.uji.ei102718cln.TooPots.model.Instructor;

@Controller
@RequestMapping("/instructor")
public class InstructorController {

	private InstructorDao instructorDao;

	@Autowired
	public void setInstructorDao(InstructorDao instructorDao) {
		this.instructorDao = instructorDao;
	}

	@RequestMapping("/list")
	public String listInstructors(Model model) {
		model.addAttribute("instructors", instructorDao.getInstructors());
		return "instructor/list";

	}

	@RequestMapping(value = "/add")
	public String addInstructor(Model model) {
		model.addAttribute("instructor", new Instructor());
		return "instructor/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("instructor") Instructor instructor, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "instructor/add";
		instructorDao.addInstructor(instructor);
		return "redirect:list";

	}

	@RequestMapping(value = "/update/{nif}", method = RequestMethod.GET)
	public String editInstructor(Model model, @PathVariable String nif) {
		model.addAttribute("instructor", instructorDao.getInstructor(nif));
		return "instructor/update";
	}

	@RequestMapping(value = "/update/{nif}", method = RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String nif, @ModelAttribute("instructor") Instructor instructor,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "instructor/update";

		instructorDao.updateInstructor(instructor);
		return "redirect:../list";
	}

	@RequestMapping(value = "/delete/{nif}")
	public String processDelete(@PathVariable String nif) {
		instructorDao.deleteInstructor(nif);
		return "redirect:../list";
	}

}
