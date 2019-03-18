package es.uji.ei102718cln.TooPots.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import es.uji.ei102718cln.TooPots.dao.CustomerDao;
import es.uji.ei102718cln.TooPots.model.Customer;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	private CustomerDao customerDao;
	
	@Autowired
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao=customerDao;
	}
	@RequestMapping("/list")
	public String listCustomers(Model model) {
		model.addAttribute("customers", customerDao.getCustomers());
		return "customer/list";
	}
	
	@RequestMapping(value="/add") 
   public String addCustomer(Model model) {
       model.addAttribute("customer", new Customer());
       return "customer/add";
   }
	@RequestMapping(value="/add", method=RequestMethod.POST) 
	public String processAddSubmit(@ModelAttribute("customer") Customer customer,
	                                BindingResult bindingResult) {  
	     if (bindingResult.hasErrors()) 
	            return "customer/add";
	     customerDao.addCustomer(customer);
	     return "redirect:list"; 
	     
	 }
	@RequestMapping(value="/update/{nom}", method = RequestMethod.GET) 
	public String editCustomer(Model model, @PathVariable String nom) { 
		model.addAttribute("activity", customerDao.getCustomer(nom));
		return "customer/update"; 
	}
	
	@RequestMapping(value="/update/{nom}", method = RequestMethod.POST) 
	public String processUpdateSubmit(@PathVariable String nom, 
                            @ModelAttribute("customer") Customer customer, 
                            BindingResult bindingResult) {
		 if (bindingResult.hasErrors()) 
			 return "customer/update";
		
		 customerDao.updateCustomer(customer);
		 return "redirect:../list"; 
	}
	@RequestMapping(value="/delete/{nom}")
	public String processDelete(@PathVariable String nom) {
           customerDao.deleteCustomer(nom);
           return "redirect:../list"; 
	}
	
	
	

}
