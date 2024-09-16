package hnqd.project.ApartmentManagement.repository;

import hnqd.project.ApartmentManagement.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFeedbackRepo extends JpaRepository<Feedback, Integer> {
    List<Feedback> findAllByUserId(int userId);
    List<Feedback> findAllByUserIdAndUserRoomId(int userId, int roomId);
    List<Feedback> findAllByUserRoomId(int roomId);
}
