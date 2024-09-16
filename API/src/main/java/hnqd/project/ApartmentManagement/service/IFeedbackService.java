package hnqd.project.ApartmentManagement.service;

import hnqd.project.ApartmentManagement.dto.FeedbackRequest;
import hnqd.project.ApartmentManagement.entity.Feedback;

import java.util.List;
import java.util.Map;

public interface IFeedbackService {
    List<Feedback> getFeedbacks(Map<String, String> params);
    Feedback getFeedbackById(int id);
    Feedback createFeedback(FeedbackRequest feedbackRequest);
    Feedback updateFeedback(int feedbackId, Map<String, String> data);

    void deleteFeedback(int id);
}
