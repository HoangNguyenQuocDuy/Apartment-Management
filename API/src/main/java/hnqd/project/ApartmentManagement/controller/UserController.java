package hnqd.project.ApartmentManagement.controller;

import hnqd.project.ApartmentManagement.dto.ResponseObject;
import hnqd.project.ApartmentManagement.dto.UserRequest;
import hnqd.project.ApartmentManagement.entity.User;
import hnqd.project.ApartmentManagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/")
    public ResponseEntity<ResponseObject> createUser(@RequestBody User u) throws IOException {
            User userSave = userService.createUser(u);
            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(
                    new ResponseObject("OK", "Create user successfully!", userSave)
            );
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ResponseObject> updateUser(@PathVariable("userId") Integer userId,
                                                     @RequestPart("file")MultipartFile file,
                                                     @RequestParam Map<String, String> params) throws IOException {
        User userSave = userService.updateUser(userId, file, params);

        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Update user successfully!", userSave)
        );
    }
}
