package hnqd.project.ApartmentManagement.repository;

import hnqd.project.ApartmentManagement.entity.Relative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRelativeRepo extends JpaRepository<Relative, Integer> {
    List<Relative> findAllByFirstnameContaining(String firstname);
    List<Relative> findAllByLastnameContaining(String firstname);
    List<Relative> findAllByType(String type);
    List<Relative> findAllByUserId(Integer id);
}
