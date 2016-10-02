package com.epam.jmp.ratkevich.dto;

import java.time.LocalDateTime;

/**
 * Contains information about a file within its metadata.
 *
 */
public class FileInfoDTO extends AbstractDomainDTO<Long> {

	private String fileName;

	private LocalDateTime uploadDate;

	private LocalDateTime expirationDate;

	private boolean isExpired;

	private byte[] data;

	public LocalDateTime getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(LocalDateTime uploadDate) {
		this.uploadDate = uploadDate;
	}

	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}

	public boolean isExpired() {
		return isExpired;
	}

	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}