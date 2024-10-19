package com.login.jwt.controller;

import com.login.jwt.dao.UserDao;
import com.login.jwt.entity.User;
import com.login.jwt.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;


    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        logger.info("User Registration is success");
        return userService.registerNewUser(user);
    }







    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }

    @GetMapping("/getUser/{userName}")
    public Optional<User> getuser(@PathVariable String userName){
        return userDao.findById(userName);
//        @RequestHeader(value="Authorization", required=false) String Authorization,

    }
//    @PutMapping("/update/{userName}")
//    public ResponseEntity<User> updateUser(
//            @PathVariable String userName,
//            @RequestPart("user") User updatedUser,  // User information in request part
//            @RequestPart(value = "profilePicture", required = false) MultipartFile profilePicture  // Profile picture
//    ) throws IOException {
//        User updated = userService.updateUser(userName, updatedUser, profilePicture);
//        return ResponseEntity.ok(updated);
//    }
@PutMapping("/update/{userName}")
public ResponseEntity<User> updateUserProfile(
        @PathVariable String userName,
        @RequestPart("user") User updatedUser,
        @RequestPart(value = "profilePicture", required = false) MultipartFile profilePicture
) throws IOException {
     updatedUser = userService.updateUser(userName, updatedUser, profilePicture);
    return ResponseEntity.ok(updatedUser);
}

    @GetMapping("/findall")
    public Iterable<User>alluser(){
        return userDao.findAll();
    }
}
