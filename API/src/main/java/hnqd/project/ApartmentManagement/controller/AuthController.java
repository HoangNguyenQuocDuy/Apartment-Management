package hnqd.project.ApartmentManagement.controller;

import hnqd.project.ApartmentManagement.dto.AuthRequest;
import hnqd.project.ApartmentManagement.dto.ResponseObject;
import hnqd.project.ApartmentManagement.service.Impl.AuthServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthServiceImpl authService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest, HttpServletResponse response) {

        try {
            return ResponseEntity.status(200).body(
                    new ResponseObject("OK", "Login successful!", authService.authenticate(authRequest, response))
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SC_EXPECTATION_FAILED).body(
                    new ResponseObject("FAILED", "Login ERROR!", e.getMessage())
            );
        }
    }

}
