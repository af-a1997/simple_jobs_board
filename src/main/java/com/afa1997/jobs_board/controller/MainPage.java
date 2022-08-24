package com.afa1997.jobs_board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainPage {
	@RequestMapping(name = "/",method = RequestMethod.GET)
	public String mainHomePage() {
		return "index";
	}
}
