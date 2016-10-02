package com.epam.jmp.ratkevich.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.epam.jmp.ratkevich.dto.FileInfoDTO;
import com.epam.jmp.ratkevich.entity.FileInfo;

@Component
public class FileMetaDataConverter implements Converter<FileInfoDTO, FileInfo> {

	private static final String EXCLUDED_PROPERTY = "data";

	@Override
	public FileInfoDTO convert(FileInfo entity) {
		FileInfoDTO fileInfoDTO = new FileInfoDTO();
		BeanUtils.copyProperties(entity, fileInfoDTO, EXCLUDED_PROPERTY);
		return fileInfoDTO;
	}

}
