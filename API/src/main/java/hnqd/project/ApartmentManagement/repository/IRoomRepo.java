package hnqd.project.ApartmentManagement.repository;

import hnqd.project.ApartmentManagement.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoomRepo extends JpaRepository<Room, Integer> {
    List<Room> findAllByStatus(String status);
}
