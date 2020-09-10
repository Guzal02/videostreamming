package com.petvideostreamingapp.controller;

import com.petvideostreamingapp.model.User;
import com.petvideostreamingapp.repo.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {
	private final MessageRepository messageRepo;

	@Autowired
	public MainController(MessageRepository messageRepo) {
		this.messageRepo = messageRepo;
	}

	@GetMapping
	public String main(Model model, @AuthenticationPrincipal User user) {
		HashMap<Object, Object> data = new HashMap<>();

		data.put("profile", user);
		data.put("messages", messageRepo.findAll());

		model.addAttribute("frontendData", data);

		return "index";
	}
}