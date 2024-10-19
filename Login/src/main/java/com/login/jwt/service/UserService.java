package com.login.jwt.service;

import com.login.jwt.dao.UserDao;
import com.login.jwt.entity.Role;
import com.login.jwt.entity.User;
import com.login.jwt.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);



        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);


    }

    public User registerNewUser(User user) {
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
    }
    public User updateUser(String userName, User updatedUser, MultipartFile profilePicture) throws IOException {
        // Find the existing user
        User existingUser = userDao.findByUserName(userName).orElseThrow(() -> new RuntimeException("User not found"));

        // Update user information
        existingUser.setUserName(updatedUser.getUserName());
        existingUser.setUserFirstName(updatedUser.getUserFirstName());
        existingUser.setUserLastName(updatedUser.getUserLastName());

        // If profile picture is uploaded, update the picture
        if (profilePicture != null && !profilePicture.isEmpty()) {
            byte[] imageBytes = profilePicture.getBytes();
            existingUser.setProfilePicture(imageBytes);  // Store the picture as bytes (BLOB)
        }

        // Save updated user information
        return userDao.save(existingUser);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
