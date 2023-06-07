package com.smhrd.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//view 반환 컨트롤러 지정
@Controller
public class HomeController {
	
//	@RequestMapping(value="/", method=RequestMethod.GET)
//	@GetMapping(value="/")
	@GetMapping("/")
	public String main() {
		return "main";
	}
}
