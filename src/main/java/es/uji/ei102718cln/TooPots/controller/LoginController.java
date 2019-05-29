package es.uji.ei102718cln.TooPots.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; 
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestMethod; 


import es.uji.ei102718cln.TooPots.dao.LoginDao;
import es.uji.ei102718cln.TooPots.model.Login;


import org.springframework.validation.Errors; 
import org.springframework.validation.Validator;



class LoginValidator implements Validator { 
	@Override
	public boolean supports(Class<?> cls) { 
		return Login.class.isAssignableFrom(cls);
	}
	@Override 
	public void validate(Object obj, Errors errors) {
	  // Exercici: Afegeix codi per comprovar que 
         // l'usuari i la contrasenya no estiguen buits 
         // ...
		Login user = (Login) obj;
		if (user.getUsuario().trim().equals(""))
			errors.rejectValue("usuario", "obligatorio", "se debe introducir un valor");
		if (user.getPassword().trim().equals(""))
			errors.rejectValue("password", "obligatorio", "se debe introducir un valor");
	}
}

@Controller
public class LoginController {
	@Autowired
	private LoginDao loginDao;

	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("usuario", new Login());
		return "login";
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String checkLogin(@ModelAttribute("usuario") Login usuario,  		
				BindingResult bindingResult, HttpSession session) {
		LoginValidator loginValidator = new LoginValidator(); 
		loginValidator.validate(usuario, bindingResult); 
		if (bindingResult.hasErrors()) {
			return "login";
		}
	       // Comprova que el login siga correcte 
		// intentant carregar les dades de l'usuari 
		usuario = loginDao.getLogin(usuario.getUsuario(),usuario.getPassword()); 
		if (usuario == null) {
			bindingResult.rejectValue("password", "badpw", "Contraseña incorrecta"); 
			return "login";
		}
		// Autenticats correctament. 
		// Guardem les dades de l'usuari autenticat a la sessió
		session.setAttribute("usuario", usuario); 
		
//		if(!session.getAttribute("nextUrl").equals(null)) {
//			String url = (String) session.getAttribute("nextUrl");
//			session.removeAttribute("nextUrl");
//			return "redirect:"+url;
//			
//		}
//		
		switch (usuario.getRol()) {
		case "customer":
			return "redirect:/customer/home";
		case "admin":
			return "redirect:/admin/home.html";
		case "instructor":
			return "redirect:/instructor/home";
		}
			
		// Torna a la pàgina principal
		return "redirect:/";
	}

	@RequestMapping("/logout") 
	public String logout(HttpSession session) {
		session.invalidate(); 
		return "redirect:/";
	}
}
