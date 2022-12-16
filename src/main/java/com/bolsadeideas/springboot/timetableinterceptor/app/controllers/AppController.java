package com.bolsadeideas.springboot.timetableinterceptor.app.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
	
	@Value("${config.start.time}")
	private Integer open;
	@Value("${config.close.time}")
	private Integer close;
	
	@GetMapping({"/","/index"})
	public String index(Model model) {
		model.addAttribute("title","Welcome to Client´s Time Table");
		return "index";
	}
	
	@GetMapping({"/closed"})
	public String closed(Model model) {
		model.addAttribute("title","Out of Service");
		StringBuilder message = new StringBuilder("Closed, please visit us from ");
		message.append(open);
		message.append(" to ");
		message.append(close);
		message.append(", thanks.");
		model.addAttribute("message",message);
		return "closed";
	}

}
