package hnqd.project.ApartmentManagement.repository;

import hnqd.project.ApartmentManagement.entity.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IThreadRepo extends JpaRepository<Thread, Integer> {
}
