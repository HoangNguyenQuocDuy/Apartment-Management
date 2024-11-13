package hnqd.project.ApartmentManagement.repository;

import hnqd.project.ApartmentManagement.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepo extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByPostId(int postId);
}
