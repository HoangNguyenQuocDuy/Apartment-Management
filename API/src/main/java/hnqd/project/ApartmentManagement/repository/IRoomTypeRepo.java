package hnqd.project.ApartmentManagement.repository;

import hnqd.project.ApartmentManagement.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoomTypeRepo extends JpaRepository<RoomType, Integer> {
}
