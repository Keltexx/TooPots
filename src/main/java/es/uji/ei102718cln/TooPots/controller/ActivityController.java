package es.uji.ei102718cln.TooPots.controller;

import java.io.IOException;

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






@Controller    
@RequestMapping("/activity")
public class ActivityController { 
	
	   private ActivityDao activityDao;

	   
	   @Autowired
	   public void setActivityDao(ActivityDao activityDao) { 
	       this.activityDao=activityDao;
	   }
	   

	   @RequestMapping("/list")
	   public String listActivities(Model model) {
	      model.addAttribute("activities", activityDao.getActivities());
	      return "activity/list";
	   
	   }
	   
	   @RequestMapping("/list_user")
	   public String listUserActivities(Model model) {
	      model.addAttribute("activities", activityDao.getActivities());
	      return "activity/list_user";
	   
	   }
		
	   

	   
		@RequestMapping(value="/add") 
	   public String addActivity(Model model) {
	       model.addAttribute("activity", new Activity());
	       return "activity/add";
	   }
		@RequestMapping(value="/add", method=RequestMethod.POST) 
		public String processAddSubmit(@ModelAttribute("activity") Activity activity,
		                                BindingResult bindingResult) {  
			ActivityValidator activityValidator = new ActivityValidator();
			activityValidator.validate(activity, bindingResult); 
			if (bindingResult.hasErrors()) 
		            return "activity/add";
		     activityDao.addActivity(activity);
		     return "redirect:list"; 
		     
		 }
		@RequestMapping(value="/update/{activityid}", method = RequestMethod.GET) 
		public String editActivity(Model model, @PathVariable String activityid) { 
			model.addAttribute("activity", activityDao.getActivity(activityid));
			return "activity/update"; 
		}
		
		@RequestMapping(value="/update/{activityid}", method = RequestMethod.POST) 
		public String processUpdateSubmit(@PathVariable String activityid, 
	                            @ModelAttribute("activity") Activity activity, 
	                            BindingResult bindingResult) {
			 if (bindingResult.hasErrors()) 
				 return "activity/update";
			
			 activityDao.updateActivity(activity);
			 return "redirect:../list"; 
		}
		@RequestMapping(value="/delete/{activityid}")
		public String processDelete(@PathVariable String activityid) {
	           activityDao.deleteActivity(activityid);
	           return "redirect:../list"; 
		}
		
		@Autowired
	    private UploadFileService uploadFileService;

//	    @GetMapping("/")
//	    public String index(){
//	        return "upluadFileView";
//	    }

	    @PostMapping("upload")
	    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
	        if (file.isEmpty()) {
	            return new ResponseEntity<Object>("Seleccionar un archivo", HttpStatus.OK);
	        }

	        try {
	            uploadFileService.saveFile(file);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return new ResponseEntity<Object>("Archivo subido correctamente", HttpStatus.OK);
	    }
		
		

}
