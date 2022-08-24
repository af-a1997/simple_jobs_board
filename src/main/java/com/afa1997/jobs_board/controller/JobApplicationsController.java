package com.afa1997.jobs_board.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.afa1997.jobs_board.model.JobApplications;
import com.afa1997.jobs_board.model.RegisteredUsers;
import com.afa1997.jobs_board.model.Vacancies;
import com.afa1997.jobs_board.repositories.RpJobApplications;
import com.afa1997.jobs_board.repositories.RpRegisteredUsers;
import com.afa1997.jobs_board.repositories.RpVacancies;

@Controller
public class JobApplicationsController {
	@Autowired
	RpJobApplications rja;
	@Autowired
	RpVacancies rv;
	@Autowired
	RpRegisteredUsers rru;
	
	// *** LIST APPLICANTS ***
	@RequestMapping(value = "/company_man/jobs/applicants/{vid}", method = RequestMethod.GET)
	public ModelAndView listApplicantsToJob(@PathVariable("vid") int p_vid) {
		ModelAndView mav_appll = new ModelAndView("pages/cmp_man/list_job_applicants");
		
		Optional<Vacancies> get_job_data = rv.findById(p_vid);
		List<RegisteredUsers> get_users_data = rru.findAll();
		List<JobApplications> get_applicants = rja.findAll();
		
		mav_appll.addObject("appll_info_rudata",get_users_data);
		mav_appll.addObject("appll_info_aplist",get_applicants);
		mav_appll.addObject("appll_info_vcidno",p_vid);
		mav_appll.addObject("appll_info_vcname",get_job_data.get().getName());
		
		return mav_appll;
	}
	
	// *** APPLY TO JOB ***
	@RequestMapping(value = "/user_man/apply_to_job/{vid}", method = RequestMethod.GET)
	public ModelAndView userAppliesToJob(@PathVariable("vid") int p_vid) {
		ModelAndView mav_appl = new ModelAndView("pages/vcn_man/apply");
		
		Optional<Vacancies> vcn_data = rv.findById(p_vid); 
		
		mav_appl.addObject("job_info_idno",p_vid);
		mav_appl.addObject("job_info_name",vcn_data.get().getName());
		
		return mav_appl;
	}
	@RequestMapping(value = "/user_man/apply_to_job/{vid}", method = RequestMethod.POST)
	//public String userAppliesToJobAct(@PathVariable("vid") int p_vid, @RequestParam("user_idno") int p_uid, JobApplications ja, BindingResult bdr, RedirectAttributes ra) {
	public String userAppliesToJobAct(@PathVariable("vid") int p_vid, @RequestParam("user_idno") int p_uid, JobApplications ja) {
		
		DateTimeFormatter set_dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime set_currtime = LocalDateTime.now();
		
		ja.setDt_apply(set_dtf.format(set_currtime));
		ja.setJob_id(p_vid);
		ja.setUser_idno(p_uid);
		
		rja.save(ja);
		
		/*
		if(!bdr.hasErrors())
			ra.addFlashAttribute("user_applied","You have successfully applied to the job.");
		else
			ra.addFlashAttribute("err_user_not_applied","An error has ocurred, so your application couldn't be sent.");
		 */
		
		return "redirect:/jobs";
	}
}
