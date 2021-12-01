package com.filehandler.controller;
import com.filehandler.repository.FileRepository; 
import com.filehandler.serviceImpl.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/file/user")
public class FileController {

    public static String loggedInUser=null;

    @Autowired
    private FileServiceImpl fileService;

    @Autowired
    FileRepository fileRepository;

//    @PostMapping("/add-file")
//    public ResponseEntity addFile(@RequestParam("file") MultipartFile file,@RequestParam("user") String user){
//        System.out.println(file);
//        return fileService.saveFile(file,user);
//    }

//    @DeleteMapping("/{fileId}")
//    public ResponseEntity deleteFile(@PathVariable("fileId") String fileId,@RequestParam("user") String userId){
//        return fileService.deleteFile(fileId,userId);
//    }

//    @PutMapping("/{fileId}")
//    public ResponseEntity updateFile(@RequestParam("file") MultipartFile file,@PathVariable("fileId") String fileId,@RequestParam("user") String userId){
//        return fileService.updateFile(file,fileId,userId);
//    }
    
    @GetMapping("/")
    public String test() {
    	System.out.println("..................WORKING................");
        return "working...";
    }

    @GetMapping("/files")
    public ResponseEntity<?> getAllFilesByUser(@RequestParam("user") String userId ){
    	
        return fileService.getAllFilesByUser(userId);
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<?> getFile(@PathVariable("fileId") String fileId,@RequestParam("user") String userId){
        return fileService.getFile(fileId,userId);
    }
    
    @GetMapping("/login")
    public void loginUser(@RequestParam("user") String userId ){
    	System.out.println("request for login");
        loggedInUser=userId;
    }

    @GetMapping("/logout")
    public void logOutUser(@RequestParam("user") String userId ){
        System.out.println("logout");
        loggedInUser=null;
    }

    }
