package hnqd.project.ApartmentManagement.repository;

import hnqd.project.ApartmentManagement.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComment extends JpaRepository<Comment, Integer> {
}
