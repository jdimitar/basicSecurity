package com.example.basicSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
	
	
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping({ "/index", "/" })
    public String index() {
        return "index";
    }
	
	@GetMapping("/admins")
	public String admins() {
		return "admins";
	}
	
	@GetMapping("/users")
	public String users() {
		return "users";
	}
	

}
