package hnqd.project.ApartmentManagement.repository;

import hnqd.project.ApartmentManagement.entity.EntryRight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEntryRightRepo extends JpaRepository<EntryRight, Integer> {
    List<EntryRight> findAllByStatus(String status);
    List<EntryRight> findAllByRelativeUserId(int userId);
    List<EntryRight> findAllByStatusAndRelativeUserId(String status, int userId);
}
