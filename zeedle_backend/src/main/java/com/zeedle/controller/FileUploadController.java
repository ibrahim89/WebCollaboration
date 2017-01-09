package com.zeedle.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
//Max uploaded file size (here it is 20MB)
@MultipartConfig(fileSizeThreshold = 20971520)
public class FileUploadController {
	
	@CrossOrigin(origins="http://localhost:8080")
	@RequestMapping(value="/upload")
	public String uploadFile(@RequestParam("uploadedFile") MultipartFile uploadedFile) {
		
		//Get name of uploaded file.
		String fileName = uploadedFile.getOriginalFilename();
		
		//Path where the uploaded file will be stored.
		String path = "C:/" + fileName;
		
		//String path = "E:/Ashwath/" + fileName;
		
		//This buffer will store the data read from 'uploadedFileRef'
		byte[] buffer = new byte[1000];
		
		//Now create the output file on the server.
		File outputFile = new File(path);
		
		FileInputStream reader = null;
		FileOutputStream writer = null;
		int totalBytes = 0;
		try{
			outputFile.createNewFile();
			
	  //Create the input stream to uploaded file to read data from it.
			reader = (FileInputStream) uploadedFile.getInputStream();
			
	 //Create writer for 'OutputFile' to write data read from
	//'UploadedFileref'
		writer = new FileOutputStream(outputFile);	
		
	// Iteratively read data from 'uploadedFileRef' and write to
	// 'OutputFile';
		int byteRead = 0;
		while  ((byteRead = reader.read(buffer)) != -1) {
			writer.write(buffer);
			totalBytes += byteRead;
		}
		}catch(IOException e){
			 e.printStackTrace();
		}finally{
			try{
				reader.close();
				writer.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return "File uploaded successfully: Total Bytes Read = " + totalBytes;
	}

}
