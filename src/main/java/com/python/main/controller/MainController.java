package com.python.main.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.python.python.service.PythonService;

@Controller
public class MainController {
	
	private final PythonService pythonService;
	
	@Autowired
	public MainController(PythonService pythoneService) {
		this.pythonService = pythoneService;
	}
	
	@GetMapping(value="/")
	public String index() {
		return "index.html";
	}
	
	@PostMapping(value="/")
	public String selectedJython(Model model, @RequestParam Map<String, String> dataMap) {
		model.addAttribute("dataList", pythonService.useJython(dataMap));
		return "result.html";
	}
	
}
