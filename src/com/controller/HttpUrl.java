package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("api/httpurl/")
public class HttpUrl {
	@RequestMapping("go")
	public ModelAndView go(String page) {
		ModelAndView mv =new ModelAndView();
		
		mv.setViewName(page);
		
		return mv;
	}
}
