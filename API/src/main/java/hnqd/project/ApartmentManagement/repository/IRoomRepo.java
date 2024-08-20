package hnqd.project.ApartmentManagement.repository;

import hnqd.project.ApartmentManagement.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoomRepo extends JpaRepository<Room, Integer> {
}
