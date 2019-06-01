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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.uji.ei102718cln.TooPots.dao.RequestDao;
import es.uji.ei102718cln.TooPots.model.Activity;
import es.uji.ei102718cln.TooPots.model.Login;
import es.uji.ei102718cln.TooPots.model.Request;

@Controller
@RequestMapping("/request")
public class RequestController {

	private RequestDao requestDao;
	
	@Autowired
	public void setRequestDao(RequestDao requestDao) {
		this.requestDao = requestDao;
	}

	@RequestMapping("/requests")
	public String listRequestsInstructor(HttpSession session, Model model) {
		Login usuario = (Login) session.getAttribute("usuario");
		model.addAttribute("requestsInstructor", requestDao.getRequestsInstructor(usuario.getUsuario()));
		return "request/requests";

	}
	
	@RequestMapping(value = "/add")
	public String addRequest(HttpSession session, Model model) {
		Login login = (Login) session.getAttribute("usuario");
		
		if (login == null) {
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "request/requests");
			return "login";
		}
		
		if(!login.getRol().equals("instructor")) {
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "request/requests");
			return "login";
		}
		model.addAttribute("request", new Request());
		return "request/add";
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
	public String processAddSubmit(HttpSession session, Model model, @ModelAttribute("request") Request request,
			@RequestParam(name = "file") MultipartFile file, BindingResult bindingResult) throws IOException {
		
		Login login = (Login) session.getAttribute("usuario");
		
		if (login == null) {
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "request/requests");
			return "login";
		}
		
		if(!login.getRol().equals("instructor")) {
			model.addAttribute("usuario", new Login());
			session.setAttribute("nextUrl", "request/requests");
			return "login";
		}
		

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

		request.setCertificateAttached("/media/" + nombre);
		if (bindingResult.hasErrors())
			return "activity/add";

		requestDao.addRequest(request);
		return "redirect:list";

	}
}
