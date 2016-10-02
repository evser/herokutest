package com.epam.jmp.ratkevich.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epam.jmp.ratkevich.service.FileService;

@Controller
@RequestMapping("/view/files")
public class FileJspController {

	@Autowired
	private FileService fileService;

	// @RequestMapping("/{id}")
	// public ModelAndView getFile(@PathVariable("id") Long id) {
	// System.out.println("FileController.method()" + id);
	// ModelAndView model = new ModelAndView("index");
	// model.addObject("id", id);
	// model.addObject("fileName", "my file");
	// return model;
	// }

	@RequestMapping("/{id}")
	public String getFile(@PathVariable("id") Long id, Model model) {
		System.out.println("FileController.method()" + id);
		model.addAttribute("id", id);
		model.addAttribute("fileName", "my file");
		return "index";
	}

	// @RequestMapping
	// public Model getFiles(Model model) {
	// model.addAttribute("files", fileService.getAll());
	// return model;
	// }

}
