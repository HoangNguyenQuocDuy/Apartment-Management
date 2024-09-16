package hnqd.project.ApartmentManagement.controller;

import hnqd.project.ApartmentManagement.dto.FeedbackRequest;
import hnqd.project.ApartmentManagement.dto.ResponseObject;
import hnqd.project.ApartmentManagement.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {
    @Autowired
    private IFeedbackService feedbackService;

    @GetMapping("/")
    public ResponseEntity<ResponseObject> getFeedbacks(@RequestParam Map<String, String> params) {
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Get feedbacks successfully!",
                        feedbackService.getFeedbacks(params))
        );
    }

    @PostMapping("/")
    public ResponseEntity<?> createFeedback(@RequestBody FeedbackRequest feedbackRequest) {
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(
                new ResponseObject("OK", "Create feedback successfully!",
                        feedbackService.createFeedback(feedbackRequest))
        );
    }

    @PutMapping("/{feedbackId}")
    public ResponseEntity<?> updateFeedback(@RequestBody Map<String, String> data,
                                              @PathVariable("feedbackId") int feedbackId) {
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Update feedback successfully!",
                        feedbackService.updateFeedback(feedbackId, data)
                )
        );
    }

    @DeleteMapping("/{feedbackId}")
    public ResponseEntity<?> deleteFeedback(@PathVariable("feedbackId") int feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Delete feedback successfully!","")
        );
    }
}
