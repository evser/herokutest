package com.epam.jmp.ratkevich.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.epam.jmp.ratkevich.entity.FileInfo;

/**
 * Contains operations for manipulations with files in DB (CRUD operations and some specific operations).
 *
 */
public interface FileRepository { // extends CrudRepository, Spring-based

	void save(FileInfo fileInfo);

	FileInfo findOne(Long id);

	List<FileInfo> findAll();

	/**
	 * Sets expired flag to true in DB for file with the specified id.
	 * 
	 * @param id - file id.
	 */
	void markAsExpired(Long id);

	LocalDateTime getUploadDate(Long id);

}
