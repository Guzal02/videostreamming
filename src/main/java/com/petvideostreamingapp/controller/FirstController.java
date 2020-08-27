package com.petvideostreamingapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FirstController {

	@GetMapping("/message")
	public Map<String, String> getResult() {
		return new HashMap<>(){{
			put("test", "test");
		}};
	}
}
