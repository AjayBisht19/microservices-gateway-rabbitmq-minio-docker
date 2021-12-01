package com.filepost.controller;

import java.util.Date;  

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.filepost.config.MQConfig;
import com.filepost.dao.FileDocument;
import com.filepost.repository.FileRepository;

@RestController
@CrossOrigin("*")
public class FilePostController {
	@Autowired
	private FileRepository fileRepository;
	@Autowired
	private RabbitTemplate rabbitTemplate;


	@PostMapping("/add-file")
	public ResponseEntity<?> addFile(@RequestParam("file") MultipartFile multipartFile,
			@RequestParam("user") String user) {
		
		FileDocument file1 = new FileDocument();
		file1.setFileName(multipartFile.getOriginalFilename());
		file1.setUser(user);
		file1.setType(multipartFile.getContentType());
		file1.setCreatedAt(new Date().toString());
		FileDocument save = fileRepository.save(file1);
		
		try {
		save.setContent(multipartFile.getBytes());
		rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, save);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return new ResponseEntity<>(save, HttpStatus.CREATED);
	}

}
