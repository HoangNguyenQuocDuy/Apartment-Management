package hnqd.project.ApartmentManagement.repository;

import hnqd.project.ApartmentManagement.entity.ParkingRight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IParkingRightRepo extends JpaRepository<ParkingRight, Integer> {
    List<ParkingRight> findByStatus(String status);
    List<ParkingRight> findAllByRelativeUserId(int id);
    List<ParkingRight> findByStatusAndRelativeUserId(String status, int id);
}
