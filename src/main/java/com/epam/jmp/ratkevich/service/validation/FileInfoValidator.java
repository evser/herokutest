package com.epam.jmp.ratkevich.service.validation;

import java.util.Arrays;

import javax.validation.ValidationException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

import com.epam.jmp.ratkevich.dto.FileInfoDTO;

@Component
public class FileInfoValidator {

	private static final String[] ALLOWED_EXTENSIONS = new String[] { "doc", "docx", "pdf", "txt", "jpeg", "jpg", "png", "bmp", "zip" };

	private static final int MAX_SIZE = 200 * 1024 * 1024; // in MB

	/**
	 * Vaildates fileInfo and throws ValidationException in case of not valid data.
	 * 
	 */
	public void vaildate(FileInfoDTO fileInfoDTO) {
		if (fileInfoDTO.getData().length > MAX_SIZE) {
			throw new ValidationException("File size should be less than " + MAX_SIZE + " (mb).");
		}

		final String extension = FilenameUtils.getExtension(fileInfoDTO.getFileName());
		if (!Arrays.asList(ALLOWED_EXTENSIONS).contains(extension)) {
			throw new ValidationException("Not allowed extension: " + extension);
		}
	}
}
