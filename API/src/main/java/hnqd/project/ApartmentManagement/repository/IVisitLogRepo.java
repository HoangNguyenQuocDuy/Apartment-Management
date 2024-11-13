package hnqd.project.ApartmentManagement.repository;

import hnqd.project.ApartmentManagement.entity.VisitLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVisitLogRepo extends JpaRepository<VisitLog, Integer> {
    Page<VisitLog> findAllByRoomId(int roomId, Pageable pageable);
}
