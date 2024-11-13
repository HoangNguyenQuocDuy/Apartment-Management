package hnqd.project.ApartmentManagement.repository;

import hnqd.project.ApartmentManagement.entity.ParkingRight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IParkingRightRepo extends JpaRepository<ParkingRight, Integer> {
    Page<ParkingRight> findByStatus(String status, Pageable pageable);
    List<ParkingRight> findAllByRelativeUserId(int userId);
}
