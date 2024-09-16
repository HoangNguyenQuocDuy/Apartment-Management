package hnqd.project.ApartmentManagement.service.Impl;

import hnqd.project.ApartmentManagement.dto.FeedbackRequest;
import hnqd.project.ApartmentManagement.entity.Feedback;
import hnqd.project.ApartmentManagement.entity.User;
import hnqd.project.ApartmentManagement.exceptions.CommonException;
import hnqd.project.ApartmentManagement.repository.IFeedbackRepo;
import hnqd.project.ApartmentManagement.repository.IUserRepo;
import hnqd.project.ApartmentManagement.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class FeedbackServiceImpl implements IFeedbackService {
    @Autowired
    private IFeedbackRepo feedbackRepo;
    @Autowired
    private IUserRepo userRepo;

    @Override
    public List<Feedback> getFeedbacks(Map<String, String> params) {
        int userId = Integer.parseInt(params.getOrDefault("userId", "0"));
        int roomId = Integer.parseInt(params.getOrDefault("roomId", "0"));

        if (userId != 0 && roomId != 0) {
            return feedbackRepo.findAllByUserIdAndUserRoomId(userId, roomId);
        } else if (userId != 0) {
            return feedbackRepo.findAllByUserId(userId);
        } else if (roomId != 0) {
            return feedbackRepo.findAllByUserRoomId(roomId);
        } else {
            return feedbackRepo.findAll();
        }
    }

    @Override
    public Feedback getFeedbackById(int id) {
        return feedbackRepo.findById(id).orElseThrow(() -> (
                new CommonException.NotFoundException("Feedback not found!")
        ));
    }

    @Override
    public Feedback createFeedback(FeedbackRequest feedbackRequest) {
        User user = userRepo.findById(feedbackRequest.getUserId()).orElseThrow(
                () -> new CommonException.NotFoundException("User not found!")
        );

        Feedback feedback = new Feedback();
        feedback.setContent(feedbackRequest.getContent());
        feedback.setUser(user);
        feedback.setTitle(feedbackRequest.getTitle());
        feedback.setCreatedAt(new Timestamp(new Date().getTime()));

        return feedbackRepo.save(feedback);
    }

    @Override
    public Feedback updateFeedback(int feedbackId, Map<String, String> data) {
        Feedback feedbackSave = feedbackRepo.findById(feedbackId).orElseThrow(
                () -> new CommonException.NotFoundException("Feedback not found!")
        );

        boolean check = false;

        if (data.get("title") != null && !data.get("title").isEmpty()) {
            feedbackSave.setTitle(data.get("title"));
            check = true;
        }
        if (data.get("content") != null && !data.get("content").isEmpty()) {
            feedbackSave.setContent(data.get("content"));
            check = true;
        }

        if (check) {
            feedbackSave.setUpdatedAt(new Timestamp(new Date().getTime()));
            return feedbackRepo.save(feedbackSave);
        } else {
            throw new CommonException.RequestBodyInvalid("No keys match with feedback column");
        }
    }

    @Override
    public void deleteFeedback(int id) {
        Feedback feedbackSave = feedbackRepo.findById(id).orElseThrow(
                () -> new CommonException.NotFoundException("Feedback not found!")
        );

        feedbackRepo.delete(feedbackSave);
    }
}
