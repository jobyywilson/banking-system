package com.oracle.bank.accountservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Joby Wilson Mathews
 *
 */
@Controller
@RequestMapping("/home")
public class HomeController {
	
	@GetMapping
	public String logginSuccess() {
		return "index";
	}

}
