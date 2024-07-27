package com.pfe.Back.controllers;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pfe.Back.DTO.fifDTO;
import com.pfe.Back.controllers.api.FIFApi;
import com.pfe.Back.service.FIFService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.Arrays;
import java.util.stream.Collectors;

@CrossOrigin(origins="*")

@RestController
public class FIFController implements FIFApi {
    
	@Autowired
    private FIFService fileService;
    

    

	public FIFController(FIFService fileService) {
		this.fileService = fileService;
	}




	@Override
	public List<fifDTO> findByOperateursId(Integer operateurId) {
		// TODO Auto-generated method stub
		return fileService.findByOperateursId(operateurId) ;
	}




	@Override
	public List<fifDTO> findAll() {
		// TODO Auto-generated method stub
		return fileService.findAll();
	}




	@Override
	public fifDTO save(fifDTO dto) {
		// TODO Auto-generated method stub
		return fileService.save(dto);
	}
    //private final String uploadDir = "C:/Users/mounir/Desktop/pfefront/src/upload"; // Update with your desired upload directory path

	//private static final String PDF_FOLDER = "C:\\wamp64\\www\\uploadfile";
	//private static final String BASE_URL = "http://localhost/uploadfile/";

	public List<String> getPdfPaths( String matricule) {
	    List<String> pdfUrls = new ArrayList<>();
	       String PDF_FOLDER = "C:\\wamp64\\www\\uploadfile\\"+matricule;
		   String BASE_URL = "http://localhost/uploadfile/"+matricule+"/";

	    try {
	        File folder = new File(PDF_FOLDER);
	        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));

	        if (files != null) {
	            pdfUrls = Arrays.stream(files)
	                            .map(file -> BASE_URL + file.getName()) // Adjusted line
	                            .collect(Collectors.toList());
	        }
	    } catch (SecurityException | NullPointerException e) {
	        System.err.println("Error while accessing folder: " + e.getMessage());
	        // Handle or log the exception as needed
	    }

	    return pdfUrls;
	}	   

}