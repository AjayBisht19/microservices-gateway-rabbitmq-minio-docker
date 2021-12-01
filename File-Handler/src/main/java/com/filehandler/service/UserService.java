package com.filehandler.service;

import com.filehandler.dao.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    public ResponseEntity<?> saveUser(User user);
    public ResponseEntity<?> loginUser(User user);


    ResponseEntity<?> logout();
}
