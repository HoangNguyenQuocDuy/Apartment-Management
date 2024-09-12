package hnqd.project.ApartmentManagement.repository;

import hnqd.project.ApartmentManagement.entity.Roomtype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoomTypeRepo extends JpaRepository<Roomtype, Integer> {
}
