package hnqd.project.ApartmentManagement.repository;

import hnqd.project.ApartmentManagement.entity.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFeedbackRepo extends JpaRepository<Feedback, Integer> {
    Page<Feedback> findAllByUserId(int userId, Pageable pageable);
    Page<Feedback> findAllByUserIdAndUserRoomId(int userId, int roomId, Pageable pageable);
    Page<Feedback> findAllByUserRoomId(int roomId, Pageable pageable);
}
