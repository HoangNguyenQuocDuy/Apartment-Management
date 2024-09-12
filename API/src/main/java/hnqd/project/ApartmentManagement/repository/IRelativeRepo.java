package hnqd.project.ApartmentManagement.repository;

import hnqd.project.ApartmentManagement.entity.Relative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRelativeRepo extends JpaRepository<Relative, Integer> {
}
