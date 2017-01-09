/*package com.zeedle.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
public static void uploadImage(String path, MultipartFile image, int imageName) {
		
		To check whether file path exist ot not if not create it
		if (!Files.exists(Paths.get(path))) {
			try {
				Files.createDirectories(Paths.get(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Now transfer the image to it destination
		try{
		image.transferTo(new File(path+imageName+".png"));
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void deleteImage(String path,int imageName){
		To check whether file path exist ot not if not create it
		if (!Files.exists(Paths.get(path))) {
			try {
				Files.createDirectories(Paths.get(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try{
			if(Files.exists(Paths.get(path+imageName+".png"))){
				Files.delete(Paths.get(path+imageName+".png"));
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new RuntimeException("Unable to delete file at "+path+imageName+".png");
		}
	}
}
*/