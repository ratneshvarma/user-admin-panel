package com.panel.controller;

import java.io.File;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.panel.response.ResponseData;

@RestController
@RequestMapping("/upload")
public class ImageUploadController {
	//create uploadingdir(i.e. /home/ratnesh/workspace/panel/panel/uploadingdir) folder to move the file form postman
	 public static final String uploadingdir = System.getProperty("user.dir") + "/uploadingdir/";

	@RequestMapping(value = "/file",  method = RequestMethod.POST)
	 public ResponseData uploadFile(@RequestParam("file") MultipartFile uploadfile) {
		ResponseData responseData = null;		
		try {
			File file = new File(uploadingdir + uploadfile.getOriginalFilename());
			uploadfile.transferTo(file);
			responseData = new ResponseData(HttpStatus.OK.value(), "File uploaded", uploadfile.getOriginalFilename()+" file succeesfully uploaded.");
		}catch (Exception e) {
			responseData = new ResponseData(HttpStatus.BAD_REQUEST.value(), "Failed", "file uploading failed.");
			
		}
		return responseData;
	}
}
