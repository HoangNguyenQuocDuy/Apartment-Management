package hnqd.project.ApartmentManagement.repository;

import hnqd.project.ApartmentManagement.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepo extends JpaRepository<Post, Integer> {
    List<Post> findAllByThreadId(int threadId);
}
