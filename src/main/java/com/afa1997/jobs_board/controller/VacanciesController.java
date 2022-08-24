package com.afa1997.jobs_board.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.afa1997.jobs_board.model.Company;
import com.afa1997.jobs_board.model.Vacancies;
import com.afa1997.jobs_board.repositories.RpCompany;
import com.afa1997.jobs_board.repositories.RpVacancies;

@Controller
public class VacanciesController {
	@Autowired
	RpVacancies rv;
	
	@Autowired
	RpCompany rc;
	
	public List<String> job_cat = Arrays.asList("Full-time", "Part time", "Freelancer","Not specified");
	public String back_to_jobs_list = "<a href='/jobs'>&blacktriangleleft; Go back to full jobs list</a>";

	// *** VACANCIES LIST ***
	@RequestMapping(value = "/jobs", method = RequestMethod.GET)
	public ModelAndView vacanciesList() {
		ModelAndView mav_vl = new ModelAndView("pages/vacancies_list");
		
		List<Vacancies> list_vacancies = rv.findAll();
		List<Company> get_companies_list = rc.findAll();
		
		mav_vl.addObject("obj_pv",list_vacancies);
		mav_vl.addObject("obj_vcn_jobcat_labels",job_cat);
		mav_vl.addObject("obj_vcn_cmp_list",get_companies_list);
		
		return mav_vl;
	}
	
	// *** VACANCY DETAILS ***
	@RequestMapping(value = "/jobs/details/{vid}", method = RequestMethod.GET)
	public ModelAndView vacancyInfo(@PathVariable("vid") int p_vid) {
		ModelAndView mav_vi = new ModelAndView("pages/vcn_man/details");
		
		Vacancies get_vcn_info = rv.findById(p_vid).get();
		
		int get_vcn_info_pubid = get_vcn_info.getPublisher();
		
		Company get_cmp_info = rc.findById(get_vcn_info_pubid).get();
		
		mav_vi.addObject("vcn_info_pubid",get_vcn_info_pubid);
		mav_vi.addObject("vcn_info_name",get_vcn_info.getName());
		mav_vi.addObject("vcn_info_jd",get_vcn_info.getJob_description());
		mav_vi.addObject("vcn_info_recruitables",get_vcn_info.getRecruitment_slots());
		mav_vi.addObject("vcn_info_recruit_begin",get_vcn_info.getDt_recruitment_begin());
		mav_vi.addObject("vcn_info_recruit_end",get_vcn_info.getDt_recruitment_end());
		mav_vi.addObject("vcn_info_cat",get_vcn_info.getCategory());
		mav_vi.addObject("vcn_info_sal",get_vcn_info.getSalary());
		mav_vi.addObject("vcn_info_vcnst",get_vcn_info.getVacancy_status());
		mav_vi.addObject("vcn_info_apt",get_vcn_info.getTerms_documentation());
		mav_vi.addObject("vcn_exin_cmp_id",get_cmp_info.getCid());
		mav_vi.addObject("vcn_exin_cmp_name",get_cmp_info.getName());
		mav_vi.addObject("vcn_exin_cmp_logofn",get_cmp_info.getLogo_filename());
		
		return mav_vi;
	}
	
	// *** POST NEW VACANCY ***
	@RequestMapping(value = "/company_man/jobs/post", method = RequestMethod.GET)
	public String postNewJob(Model p_mod) {
		p_mod.addAttribute("o_jobcat_receive",job_cat);
		
		return "pages/vcn_man/reg";
	}
	@RequestMapping(value = "/company_man/jobs/post", method = RequestMethod.POST)
	public String postNewJobAct(@Valid Vacancies in_vcn_info, @RequestParam("file") MultipartFile in_doc_data) {
		try {
			if(!in_doc_data.isEmpty()) {
				byte[] in_doc_cont = in_doc_data.getBytes();
				
				Path in_doc_saveaddr = Paths.get("./src/main/resources/static/user_uploads/job_docs/" + in_doc_data.getOriginalFilename());
				
				Files.write(in_doc_saveaddr, in_doc_cont);
				
				in_vcn_info.setTerms_documentation(in_doc_data.getOriginalFilename());
			}
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		rv.save(in_vcn_info);
		
		return "redirect:/jobs";
	}
	
	// *** UPDATE VACANCY ***
	@RequestMapping(value = "/company_man/jobs/edit/{vid}", method = RequestMethod.GET)
	public ModelAndView updateJob(@PathVariable("vid") int in_vcn_id) {
		ModelAndView mav_updvcn = new ModelAndView("pages/vcn_man/edit");
		Optional<Vacancies> get_vcn_info = rv.findById(in_vcn_id);
		
		mav_updvcn.addObject("obj_vcn_info_name",get_vcn_info.get().getName());
		mav_updvcn.addObject("obj_vcn_info_vcnst",get_vcn_info.get().getVacancy_status());
		mav_updvcn.addObject("obj_vcn_info_job_desc",get_vcn_info.get().getJob_description());
		mav_updvcn.addObject("obj_vcn_info_slots",get_vcn_info.get().getRecruitment_slots());
		mav_updvcn.addObject("obj_vcn_info_begpd",get_vcn_info.get().getDt_recruitment_begin());
		mav_updvcn.addObject("obj_vcn_info_endpd",get_vcn_info.get().getDt_recruitment_end());
		mav_updvcn.addObject("obj_vcn_info_jobcat",get_vcn_info.get().getCategory());
		mav_updvcn.addObject("obj_vcn_info_salary",get_vcn_info.get().getSalary());
		mav_updvcn.addObject("obj_vcn_info_apt",get_vcn_info.get().getTerms_documentation());
		
		mav_updvcn.addObject("obj_vcn_jobcat_labels",job_cat);
		
		return mav_updvcn;
	}
	@RequestMapping(value = "/company_man/jobs/edit/{vid}", method = RequestMethod.POST)
	public String updateJobAct(Vacancies in_vcn_info, @PathVariable("vid") int in_vcn_vid, @RequestParam("file") MultipartFile in_doc_data) {
		Vacancies vcn_to_edit = rv.findById(in_vcn_vid).orElse(null);
		
		vcn_to_edit.setName(in_vcn_info.getName());
		vcn_to_edit.setVacancy_status(in_vcn_info.getVacancy_status());
		vcn_to_edit.setRecruitment_slots(in_vcn_info.getRecruitment_slots());
		vcn_to_edit.setJob_description(in_vcn_info.getJob_description());
		vcn_to_edit.setDt_recruitment_begin(in_vcn_info.getDt_recruitment_begin());
		vcn_to_edit.setDt_recruitment_end(in_vcn_info.getDt_recruitment_end());
		vcn_to_edit.setSalary(in_vcn_info.getSalary());
		try {
			if(!in_doc_data.isEmpty()) {
				byte[] in_doc_cont = in_doc_data.getBytes();
				
				Path in_doc_saveaddr = Paths.get("./src/main/resources/static/user_uploads/job_docs/" + in_doc_data.getOriginalFilename());
				
				Files.write(in_doc_saveaddr, in_doc_cont);
				
				in_vcn_info.setTerms_documentation(in_doc_data.getOriginalFilename());
			}
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		rv.save(in_vcn_info);
		
		return "redirect:/jobs";
	}
	
	// *** JOB BROWSER ***
	@RequestMapping(value = "/jobs/browse", method = RequestMethod.POST)
	public ModelAndView browseVacancies(@RequestParam("fln_search_vcn_namelike") String in_search_param) {
		ModelAndView mav_vcnsr = new ModelAndView("pages/vacancies_list");
		
		List<Vacancies> get_jobs_by_namelike = rv.findVacanciesByNameLike("%" + in_search_param + "%");
		List<Company> get_companies_list = rc.findAll();
		
		mav_vcnsr.addObject("obj_pv",get_jobs_by_namelike);
		mav_vcnsr.addObject("obj_userisbrowsing",back_to_jobs_list);
		mav_vcnsr.addObject("obj_vcn_jobcat_labels",job_cat);
		mav_vcnsr.addObject("obj_vcn_cmp_list",get_companies_list);
		
		return mav_vcnsr;
	}
	@RequestMapping(value = "/jobs/browse/by_company/{cid}", method = RequestMethod.GET)
	public ModelAndView browseVacanciesByPublisher(@PathVariable("cid") int in_cmp_cid) {
		ModelAndView mav_vcnsr = new ModelAndView("pages/vacancies_list");
		
		List<Vacancies> get_jobs_by_pubcom = rv.findVacanciesByPublisher(in_cmp_cid);
		List<Company> get_companies_list = rc.findAll();
		
		mav_vcnsr.addObject("obj_pv",get_jobs_by_pubcom);
		mav_vcnsr.addObject("obj_userisbrowsing",back_to_jobs_list);
		mav_vcnsr.addObject("obj_vcn_jobcat_labels",job_cat);
		mav_vcnsr.addObject("obj_vcn_cmp_list",get_companies_list);
		mav_vcnsr.addObject("obj_vcn_cmp_name",rc.findById(in_cmp_cid).get().getName());
		
		return mav_vcnsr;
	}
	@RequestMapping(value = "/jobs/browse/by_category/{cat}", method = RequestMethod.GET)
	public ModelAndView browseVacanciesByCategorization(@PathVariable("cat") int in_cmp_cid) {
		ModelAndView mav_vcnsr = new ModelAndView("pages/vacancies_list");
		String cat_crit = new String();
		
		if(in_cmp_cid >= 0 && in_cmp_cid <= job_cat.size()-1) 
			cat_crit = job_cat.get(in_cmp_cid);
		else
			cat_crit = job_cat.get(job_cat.size()-1);
		
		List<Vacancies> get_jobs_by_cc = rv.findVacanciesByCategory(cat_crit);
		List<Company> get_companies_list = rc.findAll();
		
		mav_vcnsr.addObject("obj_pv",get_jobs_by_cc);
		mav_vcnsr.addObject("obj_userisbrowsing",back_to_jobs_list);
		mav_vcnsr.addObject("obj_vcn_jobcat_labels",job_cat);
		mav_vcnsr.addObject("obj_vcn_jobcat_cc",cat_crit);
		mav_vcnsr.addObject("obj_vcn_cmp_list",get_companies_list);
		
		return mav_vcnsr;
	}
}
