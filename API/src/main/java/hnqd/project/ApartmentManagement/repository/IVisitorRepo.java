package hnqd.project.ApartmentManagement.repository;

import hnqd.project.ApartmentManagement.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVisitorRepo extends JpaRepository<Visitor, Integer> {
}
