package com.epam.jmp.ratkevich.service;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;

import com.epam.jmp.ratkevich.dto.FileInfoDTO;

/**
 * Contains operations for manipulations with files (CRUD operations and some specific operations).
 *
 */
public interface FileService extends CrudService<FileInfoDTO> {

	/**
	 * Marks file as expired by id.
	 * 
	 * @param id - file id.
	 */
	void markAsExpired(Long id);

	/**
	 * Saves all files in directory.
	 * 
	 * @param sourceDir source directory that contains files for saving
	 * @param sizeLimit file size limit in KB
	 * @throws IOException
	 */
	void saveAll(Path sourceDir, Long sizeLimit, Long expirationInDays) throws IOException;

	/**
	 * @return an file's upload date
	 */
	LocalDateTime getUploadDate(Long id);

}
