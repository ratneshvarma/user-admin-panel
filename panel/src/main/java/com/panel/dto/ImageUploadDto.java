package com.panel.dto;

import org.springframework.web.multipart.MultipartFile;

public class ImageUploadDto {
	private MultipartFile multipartFile;

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
}
