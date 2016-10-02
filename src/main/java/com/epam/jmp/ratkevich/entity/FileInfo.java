package com.epam.jmp.ratkevich.entity;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Contains information about a file within its metadata.
 * 
 * Represents mapping to FILE_INFO table in DB.
 * 
 */
public class FileInfo extends AbstractDomainEntity<Long> {

	private String fileName;

	private LocalDateTime uploadDate;

	private LocalDateTime expirationDate;

	private boolean isExpired;

	private byte[] data;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

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

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public boolean isExpired() {
		return isExpired;
	}

	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}

	@Override
	public String toString() {
		return "FileInfo [fileName=" + fileName + ", uploadDate=" + uploadDate + ", expirationDate=" + expirationDate + ", isExpired=" + isExpired + ", data="
				+ Arrays.toString(data) + "]";
	}

}
