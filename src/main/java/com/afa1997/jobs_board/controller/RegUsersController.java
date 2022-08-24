package com.afa1997.jobs_board.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.afa1997.jobs_board.model.RegisteredUsers;
import com.afa1997.jobs_board.repositories.RpRegisteredUsers;

@Controller
public class RegUsersController {
	@Autowired
	RpRegisteredUsers rru;
	
	// *** USER AREA ***
	/*@RequestMapping(value = "/user_area", method = RequestMethod.GET)
	public String visitorHomePage() {
		return "pages/home_visitor";
	}*/
	@RequestMapping(value = "/user_area", method = RequestMethod.GET)
	public ModelAndView visitorHomePage() {
		ModelAndView mav_hv = new ModelAndView("pages/home_visitor");
		
		// For testing purposes only, since only companies are able to see profiles.
		List<RegisteredUsers> list_all_users = rru.findAll();
		
		mav_hv.addObject("obj_users_all",list_all_users);
		
		return mav_hv;
	}
	
	// *** USER PROFILE ***
	@RequestMapping(value = "/user/profile/{uid}", method = RequestMethod.GET)
	public ModelAndView loggedUserProfile(@PathVariable("uid") int p_uid) {
		ModelAndView mav_userprofile = new ModelAndView("pages/profile");
		
		RegisteredUsers get_user_info = rru.findById(p_uid).get();
		
		mav_userprofile.addObject("obj_user_info_name",get_user_info.getName());
		mav_userprofile.addObject("obj_user_info_surname",get_user_info.getSurname());
		mav_userprofile.addObject("obj_user_info_username",get_user_info.getUsername());
		mav_userprofile.addObject("obj_user_info_bio",get_user_info.getBiography());
		mav_userprofile.addObject("obj_user_info_hp",get_user_info.getHome_phone());
		mav_userprofile.addObject("obj_user_info_mp",get_user_info.getMob_phone());
		mav_userprofile.addObject("obj_user_info_pfp",get_user_info.getProfpic_filename());
		mav_userprofile.addObject("obj_user_info_email",get_user_info.getEmail());
		mav_userprofile.addObject("obj_user_info_ha",get_user_info.getHome_addr());
		mav_userprofile.addObject("obj_user_info_workavail",get_user_info.isAvailable_to_work());
		
		return mav_userprofile;
	}
	
	// *** REGISTER/LOGIN FORM ***
	@RequestMapping("/user/login")
	public ModelAndView userLogin() {
		return new ModelAndView("pages/login/user");
	}
	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	public String userRegisterForm() {
		return "pages/user_man/reg";
	}
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	public String userRegisterAct(@Valid RegisteredUsers d_userreg, @RequestParam("file") MultipartFile img_contents) {
    	try {
    		if(!img_contents.isEmpty()) {
    			byte[] img_contents_bytes = img_contents.getBytes();
    			Path img_fs_loc = Paths.get("./src/main/resources/static/user_uploads/profile_pictures/" + img_contents.getOriginalFilename());
    			
    			Files.write(img_fs_loc, img_contents_bytes);
    			
    			d_userreg.setProfpic_filename(img_contents.getOriginalFilename());
    		}
    	}
    	catch(IOException ioe) {
    		ioe.printStackTrace();
    	}
    	
    	rru.save(d_userreg);
    	
    	return "redirect:/user_area";
	}
	
	// *** USER PROFILE EDIT ***
	@RequestMapping(value = "/user_man/profile/edit/{uid}", method = RequestMethod.GET)
	public ModelAndView userEditProfile(@PathVariable("uid") int p_uid) {
		// TODO: ID should be acquired in another way since any user can edit the info of someone else by changing ID on URL.
		ModelAndView mav_uep = new ModelAndView("pages/user_man/edit");
		
		Optional<RegisteredUsers> get_user_info = rru.findById(p_uid);
		
		mav_uep.addObject("obj_user_info_name",get_user_info.get().getName());
		mav_uep.addObject("obj_user_info_surname",get_user_info.get().getSurname());
		mav_uep.addObject("obj_user_info_username",get_user_info.get().getUsername());
		mav_uep.addObject("obj_user_info_bio",get_user_info.get().getBiography());
		mav_uep.addObject("obj_user_info_hp",get_user_info.get().getHome_phone());
		mav_uep.addObject("obj_user_info_mp",get_user_info.get().getMob_phone());
		mav_uep.addObject("obj_user_info_pfp",get_user_info.get().getProfpic_filename());
		mav_uep.addObject("obj_user_info_email",get_user_info.get().getEmail());
		mav_uep.addObject("obj_user_info_pwd",get_user_info.get().getPassword());
		mav_uep.addObject("obj_user_info_ha",get_user_info.get().getHome_addr());
		mav_uep.addObject("obj_user_info_workavail",get_user_info.get().isAvailable_to_work());
		
		return mav_uep;
	}
	@RequestMapping(value = "/user_man/profile/edit/{uid}", method = RequestMethod.POST)
	public String userEditProfileAct(RegisteredUsers p_reg_user_data, @PathVariable("uid") int p_uid, @RequestParam("file") MultipartFile img_contents) {
    	RegisteredUsers reg_user_find = rru.findById(p_uid).orElse(null);
    	
    	reg_user_find.setName(p_reg_user_data.getName());
    	reg_user_find.setSurname(p_reg_user_data.getSurname());
    	reg_user_find.setUsername(p_reg_user_data.getUsername());
    	reg_user_find.setEmail(p_reg_user_data.getEmail());
    	reg_user_find.setPassword(p_reg_user_data.getPassword());
    	reg_user_find.setBiography(p_reg_user_data.getBiography());
    	reg_user_find.setHome_phone(p_reg_user_data.getHome_phone());
    	reg_user_find.setMob_phone(p_reg_user_data.getMob_phone());
    	reg_user_find.setHome_addr(p_reg_user_data.getHome_addr());
    	reg_user_find.setAvailable_to_work(p_reg_user_data.isAvailable_to_work());
    	try {
    		if(!img_contents.isEmpty()) {
    			byte[] img_contents_bytes = img_contents.getBytes();
    			Path img_fs_loc = Paths.get("./src/main/resources/static/user_uploads/profile_pictures/" + img_contents.getOriginalFilename());
    			
    			Files.write(img_fs_loc, img_contents_bytes);
    			
    			reg_user_find.setProfpic_filename(img_contents.getOriginalFilename());
    		}
    	}
    	catch(IOException ioe) {
    		ioe.printStackTrace();
    	}
    	
    	rru.save(reg_user_find);
    	
    	return "redirect:/user_area";
	}
	
	// *** FETCH PROFILE PICTURE ***
	@RequestMapping(value = "/user_uploads/profile_pictures/{img_fn}",method = RequestMethod.GET)
	@ResponseBody
	public byte[] getUserPFP(@PathVariable("img_fn") String p_img_fn) throws IOException {
		File f_img = new File("./src/main/resources/static/user_uploads/profile_pictures/" + p_img_fn);
		
    	if(p_img_fn != null || p_img_fn.trim().length() > 0)
    		return Files.readAllBytes(f_img.toPath());
    	
    	return null;
	}
}
