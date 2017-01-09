package com.zeedle.config;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	public static void upload(Path path,MultipartFile file,String fileName){
		if(!file.isEmpty()){
			InputStream inputStream=null;
			OutputStream outputStream=null;
			
			if(file.getSize()>0){
				try{
				inputStream=file.getInputStream();
				outputStream=new FileOutputStream(path+fileName);
				int readBytes=0;
				byte[] buffer=new byte[1024];
				while((readBytes=inputStream.read(buffer, 0, 1024))!=-1){
					outputStream.write(buffer, 0, readBytes);
				}
				}catch(IOException e){
					e.printStackTrace();
				}
				finally{
					try{
						outputStream.close();
						inputStream.close();
					}catch(IOException e){
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void upload(String string, MultipartFile file, String fileName) {
		// TODO Auto-generated method stub
		
	}

}
