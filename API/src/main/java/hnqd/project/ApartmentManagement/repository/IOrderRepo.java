package hnqd.project.ApartmentManagement.repository;

import hnqd.project.ApartmentManagement.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepo extends JpaRepository<Order, Integer> {
    List<Order> findAllByLockerId(int lockerId);
    List<Order> findAllByStatus(String status);
}
