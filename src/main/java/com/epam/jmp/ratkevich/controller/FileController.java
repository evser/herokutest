package com.epam.jmp.ratkevich.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.epam.jmp.ratkevich.dto.FileInfoDTO;
import com.epam.jmp.ratkevich.service.FileService;

@RestController
@RequestMapping("/files")
public class FileController {

	@Autowired
	private FileService fileService;

	@GetMapping
	public List<FileInfoDTO> getFiles() {
		return fileService.getAll();
	}

	@GetMapping("/{id}")
	public FileInfoDTO getFile(@PathVariable("id") Long id) {
		return fileService.findOne(id);
	}

	@PostMapping
	public void uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			final FileInfoDTO fileInfoDTO = new FileInfoDTO();
			fileInfoDTO.setFileName(file.getOriginalFilename());
			fileInfoDTO.setData(file.getBytes());
			fileService.save(fileInfoDTO);
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

	@PatchMapping("/{id}/mark_expired")
	public void markExpired(@PathVariable("id") Long id) {
		fileService.markAsExpired(id);
	}
}
