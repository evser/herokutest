package com.epam.jmp.ratkevich.service;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.jmp.ratkevich.converter.FileMetaDataConverter;
import com.epam.jmp.ratkevich.dto.FileInfoDTO;
import com.epam.jmp.ratkevich.entity.FileInfo;
import com.epam.jmp.ratkevich.repository.FileRepository;
import com.epam.jmp.ratkevich.service.validation.FileInfoValidator;

@Service
public class FileServiceImpl implements FileService {

	private static final int EXPIRATION_IN_DAYS = 1;

	@Autowired
	private FileRepository fileRepository;

	@Autowired
	private FileMetaDataConverter fileConverter;

	@Autowired
	private FileInfoValidator fileInfoValidator;

	@Override
	public void save(FileInfoDTO sourceFileDTO) {
		fileInfoValidator.vaildate(sourceFileDTO);

		final FileInfo fileInfo = new FileInfo();
		fileInfo.setFileName(sourceFileDTO.getFileName());
		fileInfo.setUploadDate(LocalDateTime.now());
		fileInfo.setExpirationDate(LocalDateTime.now().plusDays(EXPIRATION_IN_DAYS));
		fileInfo.setExpired(false);
		fileInfo.setData(sourceFileDTO.getData());
		fileRepository.save(fileInfo);
	}

	@Override
	public FileInfoDTO findOne(Long id) {
		return fileConverter.convert(fileRepository.findOne(id));
	}

	@Override
	public List<FileInfoDTO> getAll() {
		return fileRepository.findAll().stream().map(fileConverter::convert).collect(Collectors.toList());
	}

	@Override
	public void markAsExpired(Long id) {
		fileRepository.markAsExpired(id);
	}

	@Override
	public LocalDateTime getUploadDate(Long id) {
		return fileRepository.getUploadDate(id);
	}

	@Override
	public void saveAll(Path sourceDir, Long sizeLimit, Long expirationInDays) throws IOException {
		throw new UnsupportedOperationException(); // TODO
	}
}
