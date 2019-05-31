package es.uji.ei102718cln.TooPots.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.uji.ei102718cln.TooPots.dao.ActivityDao;

import es.uji.ei102718cln.TooPots.model.Activity;
import es.uji.ei102718cln.TooPots.model.Login;

@Controller
@RequestMapping("/activity")
public class ActivityController {

	private ActivityDao activityDao;

	@Autowired
	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}
	@RequestMapping("/activity")
	public String listActivity(Model model) {
		model.addAttribute("activities", activityDao.getActivities());
		return "activity/activity";

	}
	@RequestMapping("/list")
	public String listActivities(Model model) {
		model.addAttribute("activities", activityDao.getActivities());
		return "activity/list";

	}
	@RequestMapping("/gallery")
	public String gallery(Model model) {
		model.addAttribute("activities", activityDao.getActivities());
		return "activity/gallery";

	}
	
	@RequestMapping("/gallery_global")
	public String galleryGlobal(Model model) {
		model.addAttribute("activities", activityDao.getActivities());
		return "activity/gallery_global";

	}
	
	@RequestMapping("/gallery_instructor")
	public String galleryInstructor(Model model) {
		model.addAttribute("activities", activityDao.getActivities());
		return "activity/gallery_instructor";

	}

	@RequestMapping("/list_user")
	public String listUserActivities(Model model) {
		model.addAttribute("activities", activityDao.getActivities());
		return "activity/list_user";

	}

	@RequestMapping(value = "/add")
	public String addActivity(HttpSession session, Model model) {
		Login login = (Login) session.getAttribute("usuario");
		
		if (login == null) {
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "activity/gallery");
			return "login";
		}
		
		if(!login.getRol().equals("instructor")) {
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "activity/gallery");
			return "login";
		}
		model.addAttribute("activity", new Activity());
		return "activity/add";
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
	public String processAddSubmit(HttpSession session, Model model, @ModelAttribute("activity") Activity activity,
			@RequestParam(name = "file") MultipartFile file, BindingResult bindingResult) throws IOException {
		
		Login login = (Login) session.getAttribute("usuario");
		
		if (login == null) {
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "activity/gallery");
			return "login";
		}
		
		if(!login.getRol().equals("instructor")) {
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "activity/gallery");
			return "login";
		}
		
		ActivityValidator activityValidator = new ActivityValidator();
		activityValidator.validate(activity, bindingResult);

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

		activity.setPhoto("/media/" + nombre);
		if (bindingResult.hasErrors())
			return "activity/add";

		activityDao.addActivity(activity);
		return "redirect:list";

	}

	@RequestMapping(value = "/update/{activityid}", method = RequestMethod.GET)
	public String editActivity(HttpSession session,Model model, @PathVariable String activityid) {
		Login login = (Login) session.getAttribute("usuario");
		
		if (login == null) {
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "activity/gallery");
			return "login";
		}
		
		if(!login.getRol().equals("instructor")) {
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "activity/gallery");
			return "login";
		}
		model.addAttribute("activity", activityDao.getActivity(activityid));
		return "activity/update";
	}

	@RequestMapping(value = "/update/{activityid}", method = RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String activityid, @ModelAttribute("activity") Activity activity,
			@RequestParam(name = "file") MultipartFile file, BindingResult bindingResult) throws IOException {
		if (bindingResult.hasErrors())
			return "activity/update";
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

		activity.setPhoto("/media/" + nombre);
		activityDao.updateActivity(activity);
		return "redirect:../list";
	}
	
	//MIRA ESTO A VERRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
	@RequestMapping(value = "/activity/{activityid}", method = RequestMethod.GET)
	public String listActivity(Model model, @PathVariable String activityid) {
		model.addAttribute("activity", activityDao.getActivity(activityid));
		return "activity/activity";
	}

	@RequestMapping(value = "/delete/{activityid}")
	public String processDelete(@PathVariable String activityid) {
		activityDao.deleteActivity(activityid);
		return "redirect:../list";
	}

}
