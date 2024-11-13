package hnqd.project.ApartmentManagement.controller;

import hnqd.project.ApartmentManagement.dto.ResponseObject;
import hnqd.project.ApartmentManagement.service.IThreadService;
import hnqd.project.ApartmentManagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/threads")
public class ThreadController {
    @Autowired
    private IThreadService threadService;
    @Autowired
    private IUserService userService;

    @PostMapping("/")
    public ResponseEntity<ResponseObject> createThread(@RequestBody Map<String, String> data,
                                                       @AuthenticationPrincipal UserDetails userDetails) {
        int userId = userService.getUserByUsername(userDetails.getUsername()).getId();

        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(
                new ResponseObject("OK", "Create thread successfully!",
                        threadService.createThread(userId, data.get("title")))
        );
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject> getThreads(@RequestParam Map<String, String> params) {
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Get threads successfully!",
                        threadService.getThreads(params))
        );
    }

    @PutMapping("/{threadId}")
    public ResponseEntity<ResponseObject> updateThread(
            @PathVariable("threadId") int threadId,
            @RequestBody Map<String, String> data,
            @AuthenticationPrincipal UserDetails userDetails) {
        int userId = userService.getUserByUsername(userDetails.getUsername()).getId();

        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Update threads successfully!",
                        threadService.updateThread(userId, threadId, data.get("title")))
        );
    }

    @DeleteMapping("/{threadId}")
    public ResponseEntity<ResponseObject> deleteThread(
            @PathVariable("threadId") int threadId,
            @AuthenticationPrincipal UserDetails userDetails) {

        int userId = userService.getUserByUsername(userDetails.getUsername()).getId();

        threadService.deleteThread(userId, threadId);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Delete threads successfully!","")
        );
    }
}
