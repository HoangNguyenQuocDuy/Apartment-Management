package hnqd.project.ApartmentManagement.repository;

import hnqd.project.ApartmentManagement.entity.EntryRight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEntryRightRepo extends JpaRepository<EntryRight, Integer> {
    Page<EntryRight> findAllByStatus(String status, Pageable pageable);
    Page<EntryRight> findAllByRelativeUserId(int userId, Pageable pageable);
    Page<EntryRight> findAllByStatusAndRelativeUserId(String status, int userId, Pageable pageable);
    List<EntryRight> findAllByRelativeUserId(int userId);
}
