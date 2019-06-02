package es.uji.ei102718cln.TooPots.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.uji.ei102718cln.TooPots.dao.InstructorDao;
import es.uji.ei102718cln.TooPots.dao.LoginDao;
import es.uji.ei102718cln.TooPots.dao.RequestDao;
import es.uji.ei102718cln.TooPots.model.Instructor;
import es.uji.ei102718cln.TooPots.model.Login;

@Controller
@RequestMapping("/instructor")
public class InstructorController {

	private InstructorDao instructorDao;
	private LoginDao loginDao;

	@Autowired
	public void setInstructorDao(InstructorDao instructorDao) {
		this.instructorDao = instructorDao;
	}

	@Autowired
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
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

	private static File transform(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("instructor") Instructor instructor,
			@RequestParam(name = "file") MultipartFile file, BindingResult bindingResult) throws IOException {
		if (bindingResult.hasErrors())
			return "instructor/add";

		/*
		 * Se convierte el MultipartFile a File, se escribe el archivo en su ruta
		 * automática, se toma la ruta absoluta del archivo, se corta el nombre del
		 * archivo para conseguir la ruta absoluta del directorio, se crea un File en el
		 * directorio media
		 */
		File archivo = transform(file);
		archivo.createNewFile();
		String directorio = archivo.getAbsolutePath();
		directorio = directorio.substring(0, directorio.length() - archivo.getPath().length() - 1);
		String nombre = archivo.getName();

		File destination = new File(directorio + "/src/main/resources/static/media/" + nombre);

		/* Copiar fichero a directorio destino */

		FileUtils.copyFile(archivo, destination);

		/*
		 * Se escribe el fichero en el directorio media se borra el archivo del
		 * directorio por defecto
		 */
		destination.createNewFile();
		archivo.delete();
		instructor.setPhoto("/media/" + nombre);
		instructor.setState("pending");
		instructorDao.addInstructor(instructor);
		Login login = new Login(instructor.getNif(), instructor.getPassword(), "instructor");
		loginDao.addLogin(login);
		return "redirect:list";

	}

	@RequestMapping(value = "/update/{nif}", method = RequestMethod.GET)
	public String editInstructor(Model model, @PathVariable String nif) {
		model.addAttribute("instructor", instructorDao.getInstructor(nif));
		return "instructor/update";
	}

	@RequestMapping(value = "/update/{nif}", method = RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String nif, @ModelAttribute("instructor") Instructor instructor,
			@RequestParam(name = "file") MultipartFile file, BindingResult bindingResult) throws IOException {
		if (bindingResult.hasErrors())
			return "instructor/update";
		/*
		 * Se convierte el MultipartFile a File, se escribe el archivo en su ruta
		 * automática, se toma la ruta absoluta del archivo, se corta el nombre del
		 * archivo para conseguir la ruta absoluta del directorio, se crea un File en el
		 * directorio media
		 */
		File archivo = transform(file);
		archivo.createNewFile();
		String directorio = archivo.getAbsolutePath();
		directorio = directorio.substring(0, directorio.length() - archivo.getPath().length() - 1);
		String nombre = archivo.getName();

		File destination = new File(directorio + "/src/main/resources/static/media/" + nombre);

		/* Copiar fichero a directorio destino */

		FileUtils.copyFile(archivo, destination);

		/*
		 * Se escribe el fichero en el directorio media se borra el archivo del
		 * directorio por defecto
		 */
		destination.createNewFile();
		archivo.delete();

		instructor.setPhoto("/media/" + nombre);


		instructorDao.updateInstructor(instructor);
		return "redirect:../list";
	}

	@RequestMapping(value = "/delete/{nif}")
	public String processDelete(@PathVariable String nif) {
		instructorDao.deleteInstructor(nif);
		return "redirect:../list";
	}

	@RequestMapping(value = "/accept/{nif}")
	public String processAccept(@PathVariable String nif) {
		instructorDao.updateInstructorStateAccept(nif);
		return "redirect:../list";
	}

	@RequestMapping(value = "/reject/{nif}")
	public String processReject(@PathVariable String nif) {
		instructorDao.updateInstructorStateReject(nif);
		return "redirect:../list";
	}
}
