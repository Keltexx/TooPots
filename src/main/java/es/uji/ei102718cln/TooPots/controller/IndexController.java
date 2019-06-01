package es.uji.ei102718cln.TooPots.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/customer/home")
	public String index_customer() {
		return "customer/home";
	}
	
	@RequestMapping("/instructor/home")
	public String index_instructor() {
		return "instructor/home";
	}
	
	@RequestMapping("/admin/home")
	public String index_admin() {
		return "admin/home";
	}
	
}
