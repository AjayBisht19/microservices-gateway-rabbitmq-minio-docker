//package com.filehandler.config;
//
//import com.filehandler.dao.User;
//import com.filehandler.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    public UserRepository userRepository;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        System.out.println("in load user by name");
//        Optional<User> optionalUser=userRepository.findByUsername(s);
//        if(optionalUser.isEmpty()){
//            throw new UsernameNotFoundException("User not found");
//        }else{
//            return new CustomSecurityUser(optionalUser.get());
//        }
//
//    }
//}
