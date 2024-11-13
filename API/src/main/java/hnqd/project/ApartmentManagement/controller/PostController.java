package hnqd.project.ApartmentManagement.controller;

import hnqd.project.ApartmentManagement.dto.PostRequest;
import hnqd.project.ApartmentManagement.dto.ResponseObject;
import hnqd.project.ApartmentManagement.service.IPostService;
import hnqd.project.ApartmentManagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private IPostService postService;
    @Autowired
    private IUserService userService;

    @PostMapping("/")
    public ResponseEntity<ResponseObject> createPost(
            @RequestPart MultipartFile[] files,
            @RequestBody Map<String, String> data,
            @AuthenticationPrincipal UserDetails userDetails) throws IOException {
        int userId = userService.getUserByUsername(userDetails.getUsername()).getId();

        PostRequest postRequest = PostRequest.builder()
                .files(files)
                .title(data.get("title"))
                .userId(userId)
                .threadId(Integer.parseInt(data.get("threadId")))
                .build();

        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(
                new ResponseObject("OK", "Create post successfully!",
                        postService.createPost(postRequest))
        );
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject> getPosts(@RequestParam Map<String, String> params) {
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Get posts by thread ID successfully!",
                        postService.getPosts(params))
        );
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ResponseObject> deletePost(@PathVariable int postId,
                                                     @AuthenticationPrincipal UserDetails userDetails) {
        int userId = userService.getUserByUsername(userDetails.getUsername()).getId();
        postService.deletePost(userId, postId);

        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Delete post successfully!","")
        );
    }

    @PutMapping("/{postId}")
    public ResponseEntity<ResponseObject> updatePost(@PathVariable("postId") int postId,
                                                     @AuthenticationPrincipal UserDetails userDetails) {
        int userId = userService.getUserByUsername(userDetails.getUsername()).getId();

        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Likes post successfully!", postService.likedPost(postId, userId))
        );
    }
}
