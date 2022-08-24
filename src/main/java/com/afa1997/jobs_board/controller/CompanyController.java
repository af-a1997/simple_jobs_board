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

import com.afa1997.jobs_board.model.Company;
import com.afa1997.jobs_board.model.RegisteredUsers;
import com.afa1997.jobs_board.model.Vacancies;
import com.afa1997.jobs_board.repositories.RpCompany;
import com.afa1997.jobs_board.repositories.RpRegisteredUsers;
import com.afa1997.jobs_board.repositories.RpVacancies;

@Controller
public class CompanyController {
	@Autowired
	RpCompany rc;
	
	@Autowired
	RpRegisteredUsers rru;
	
	@Autowired
	RpVacancies rv;
	
	@RequestMapping(value = "/companies", method = RequestMethod.GET)
	public ModelAndView companyHomePage() {
		ModelAndView mav_companies = new ModelAndView("pages/home_company");
		
		List<Company> list_all_companies = rc.findAll();
		
		mav_companies.addObject("obj_all_companies",list_all_companies);
		
		return mav_companies;
	}
	
	@RequestMapping("/companies/login")
	public ModelAndView companyLogin() {
		return new ModelAndView("pages/login/company");
	}
	
	@RequestMapping(value = "/companies/register", method = RequestMethod.GET)
	public String companyRegisterForm() {
		return "pages/cmp_man/reg";
	}
	
	@RequestMapping(value = "/companies/register", method = RequestMethod.POST)
	public String companyRegisterAct(@RequestParam("file") MultipartFile p_logo_data, @Valid Company p_cmp_data) {
		try {
			if(!p_logo_data.isEmpty()) {
				byte[] logo_bytes = p_logo_data.getBytes();
				
				Path logo_loc = Paths.get("./src/main/resources/static/user_uploads/company_logos/" + p_logo_data.getOriginalFilename());
				
				Files.write(logo_loc, logo_bytes);
				
				p_cmp_data.setLogo_filename(p_logo_data.getOriginalFilename());
			}
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		rc.save(p_cmp_data);
		
		return "redirect:/companies";
	}
	
	@RequestMapping(value = "/companies/details/{cid}", method = RequestMethod.GET)
	public ModelAndView companyDetails(@PathVariable("cid") int p_cid) {
		ModelAndView mav_cd = new ModelAndView("pages/cmp_man/details");
		
		Company get_cmp_info = rc.findById(p_cid).get();
		
		mav_cd.addObject("obj_cmp_info", get_cmp_info);
		
		return mav_cd;
	}
	
	@RequestMapping(value = "/company_man/profile/edit/{cid}", method = RequestMethod.GET)
	public ModelAndView companyEditTempl(@PathVariable("cid") int p_cid) {
		ModelAndView mav_cmped = new ModelAndView("pages/cmp_man/edit");
		
		Optional<Company> get_cmp_data = rc.findById(p_cid);
		
		mav_cmped.addObject("obj_cmp_name",get_cmp_data.get().getName());
		mav_cmped.addObject("obj_cmp_desc",get_cmp_data.get().getDescription());
		mav_cmped.addObject("obj_cmp_cntemail",get_cmp_data.get().getContact_email());
		mav_cmped.addObject("obj_cmp_bldad",get_cmp_data.get().getBuilding_addr());
		mav_cmped.addObject("obj_cmp_logofn",get_cmp_data.get().getLogo_filename());
		mav_cmped.addObject("obj_cmp_phl",get_cmp_data.get().getContact_phone_local());
		mav_cmped.addObject("obj_cmp_phm",get_cmp_data.get().getContact_phone_mob());
		
		return mav_cmped;
	}
	
	@RequestMapping(value = "/company_man/profile/edit/{cid}", method = RequestMethod.POST)
	public String companyEditAct(@PathVariable("cid") int p_cid, @RequestParam("file") MultipartFile p_logo_data, Company p_cmpdata) {
		Company cmp_to_upd = rc.findById(p_cid).orElse(null);
		
		cmp_to_upd.setName(p_cmpdata.getName());
		cmp_to_upd.setDescription(p_cmpdata.getDescription());
		cmp_to_upd.setContact_email(p_cmpdata.getContact_email());
		cmp_to_upd.setBuilding_addr(p_cmpdata.getBuilding_addr());
		cmp_to_upd.setContact_phone_local(p_cmpdata.getContact_phone_local());
		cmp_to_upd.setContact_phone_mob(p_cmpdata.getContact_phone_mob());

		try {
			if(!p_logo_data.isEmpty()) {
				byte[] logo_bytes = p_logo_data.getBytes();
				
				Path logo_loc = Paths.get("./src/main/resources/static/user_uploads/company_logos/" + p_logo_data.getOriginalFilename());
				
				Files.write(logo_loc, logo_bytes);
				
				cmp_to_upd.setLogo_filename(p_logo_data.getOriginalFilename());
			}
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		rc.save(cmp_to_upd);
		
		return "redirect:/companies";
	}
	
	// *** FETCH COMPANY LOGO ***
	@RequestMapping(value = "/user_uploads/company_logos/{img_fn}", method = RequestMethod.GET)
	@ResponseBody
	public byte[] getCmpL(@PathVariable("img_fn") String p_img_fn) throws IOException {
		File f_logo = new File("./src/main/resources/static/user_uploads/company_logos/" + p_img_fn);
		
		if(p_img_fn != null || p_img_fn.trim().length() > 0)
			return Files.readAllBytes(f_logo.toPath());
		
		return null;
	}
}
