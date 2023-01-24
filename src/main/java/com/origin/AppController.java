package com.origin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {	
	
	@GetMapping("/cog")
	public String viewHomePage() {
		return "index";		
	}
}
