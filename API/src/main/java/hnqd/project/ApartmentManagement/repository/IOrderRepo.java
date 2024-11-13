package hnqd.project.ApartmentManagement.repository;

import hnqd.project.ApartmentManagement.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepo extends JpaRepository<Order, Integer> {
    Page<Order> findAllByLockerId(int lockerId, Pageable pageable);
    Page<Order> findAllByStatus(String status, Pageable pageable);
}
