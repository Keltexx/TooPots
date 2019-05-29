package es.uji.ei102718cln.TooPots.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
		
	@RequestMapping("/register")
	public String register() {
		return "register/register";
	}
	
	@RequestMapping("/register_customer")
	public String registerCustomer() {
		return "register/register_customer";
	}
	
	@RequestMapping("/register_instructor")
	public String registerInstructor() {
		return "register/register_instructor";
	}
	
	
}
