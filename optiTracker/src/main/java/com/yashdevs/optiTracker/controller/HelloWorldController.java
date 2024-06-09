package com.yashdevs.optiTracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping(path = "/")
	public String helloTest() {
		return "hello API";
	}

	/*
	 * @GetMapping("/hello") public String greeting(Model model) { return "success";
	 * }
	 */
}
