package hnqd.project.ApartmentManagement.service;

import hnqd.project.ApartmentManagement.dto.FeedbackRequest;
import hnqd.project.ApartmentManagement.entity.Feedback;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IFeedbackService {
    Page<Feedback> getFeedbacks(Map<String, String> params);
    Feedback getFeedbackById(int id);
    Feedback createFeedback(FeedbackRequest feedbackRequest);
    Feedback updateFeedback(int feedbackId, Map<String, String> data);

    void deleteFeedback(int id);
}
