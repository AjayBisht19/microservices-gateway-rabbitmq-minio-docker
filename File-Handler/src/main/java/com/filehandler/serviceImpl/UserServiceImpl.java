//package com.filehandler.serviceImpl;
//
////import com.filehandler.config.UserDetailsServiceImpl;
//import com.filehandler.dao.User;
//import com.filehandler.repository.UserRepository;
//import com.filehandler.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.stereotype.Service;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import java.util.Optional;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    public String loggedInUser=null;
//
////    @Autowired
////    private AuthenticationManager authenticationManager;
//
////    @Autowired
////    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public ResponseEntity saveUser(User user) {
//        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
//        if(optionalUser.isPresent()){
//           return ResponseEntity.badRequest().body("Username is already taken");
//        }else{
//            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//            return ResponseEntity.ok(userRepository.save(user));
//        }
//
//
//    }
//
//    @Override
//    public ResponseEntity loginUser(User user) {
//        System.out.println("inside service impl login user");
//        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//        User user1 = userRepository.findByUsername(user.getUsername()).get();
//        loggedInUser=user1.getId();
//        System.out.println("logged in user "+loggedInUser);
//        return ResponseEntity.ok(user1);
//    }
//
//    @Override
//    public ResponseEntity logout() {
//        loggedInUser=null;
//        return ResponseEntity.ok("logged out successfully");
//    }
//
//}
