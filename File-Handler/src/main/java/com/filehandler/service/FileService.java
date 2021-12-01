package com.filehandler.service;

import org.springframework.http.ResponseEntity; 

import java.io.IOException;

public interface FileService {

//    public ResponseEntity saveFile(MultipartFile file,String userId);
    public ResponseEntity<?> getFile(String fileId,String userId) throws IOException;

//    public ResponseEntity deleteFile(String fileId,String userId);
//    public ResponseEntity updateFile(MultipartFile file,String fileId,String userId);


    public ResponseEntity<?> getAllFilesByUser(String userId);
}
