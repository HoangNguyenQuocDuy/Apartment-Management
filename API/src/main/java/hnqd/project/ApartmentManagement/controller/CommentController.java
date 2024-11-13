package hnqd.project.ApartmentManagement.controller;

import hnqd.project.ApartmentManagement.dto.CommentRequestDto;
import hnqd.project.ApartmentManagement.dto.ResponseObject;
import hnqd.project.ApartmentManagement.service.ICommentService;
import hnqd.project.ApartmentManagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private ICommentService commentService;
    @Autowired
    private IUserService userService;

    @PostMapping("/")
    public ResponseEntity<ResponseObject> createComment(
            @RequestBody CommentRequestDto commentRequest,
            @AuthenticationPrincipal UserDetails userDetails) {
        int userId = userService.getUserByUsername(userDetails.getUsername()).getId();
        commentRequest.setUserId(userId);

        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(
                new ResponseObject("OK", "Create comment successfully!",
                        commentService.createComment(commentRequest))
        );
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject> getComments(@RequestParam Map<String, String> params) {
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Get comments successfully!",
                        commentService.getAllCommentsByPostId(Integer.parseInt(params.get("postId"))))
        );
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<ResponseObject> deleteComment(@PathVariable("commentId") int commentId,
                                                        @AuthenticationPrincipal UserDetails userDetails) {
        int userId = userService.getUserByUsername(userDetails.getUsername()).getId();
        commentService.deleteComment(commentId, userId);

        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Delete comment successfully!","")
        );
    }


}
