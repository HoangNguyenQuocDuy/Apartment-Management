package hnqd.project.ApartmentManagement.service.Impl;

import hnqd.project.ApartmentManagement.dto.VisitLogRequest;
import hnqd.project.ApartmentManagement.entity.Room;
import hnqd.project.ApartmentManagement.entity.VisitLog;
import hnqd.project.ApartmentManagement.entity.Visitor;
import hnqd.project.ApartmentManagement.exceptions.CommonException;
import hnqd.project.ApartmentManagement.repository.IRoomRepo;
import hnqd.project.ApartmentManagement.repository.IVisitLogRepo;
import hnqd.project.ApartmentManagement.repository.IVisitorRepo;
import hnqd.project.ApartmentManagement.service.IVisitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class VisitLogServiceImpl implements IVisitLogService {
    @Autowired
    private IVisitLogRepo visitLogRepo;
    @Autowired
    private IRoomRepo roomRepo;
    @Autowired
    private IVisitorRepo visitorRepo;

    @Override
    public VisitLog createVisitLog(VisitLogRequest visitLogReq) {
        Visitor visitor = visitorRepo.findById(visitLogReq.getVisitorId()).orElseThrow(
                () -> new CommonException.NotFoundException("Visitor not found with ID: " + visitLogReq.getVisitorId())
        );

        Room room = roomRepo.findById(visitLogReq.getRoomId()).orElseThrow(
                () -> new CommonException.NotFoundException("Room not found with ID: " + visitLogReq.getVisitorId())
        );

        VisitLog visitLog = new VisitLog();
        visitLog.setEntryTime(visitLogReq.getEntryTime());
        visitLog.setExitTime(visitLogReq.getExitTime());
        visitLog.setCreatedAt(new Timestamp(new Date().getTime()));
        visitLog.setRoom(room);
        visitLog.setStatus("Pending");
        visitLog.setVisitor(visitor);

        return visitLogRepo.save(visitLog);
    }

    @Override
    public List<VisitLog> getVisitLogs() {
        return visitLogRepo.findAll();
    }

    @Override
    public VisitLog updateVisitLog(int visitLogId, Map<String, String> params) {
        VisitLog visitLog = visitLogRepo.findById(visitLogId).orElseThrow(
                () -> new CommonException.NotFoundException("VisitLog not found with ID: " + visitLogId)
        );

        boolean check = false;

        if (params.get("exitTime") != null && !params.get("exitTime").isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime date = LocalDateTime.parse(params.get("exitTime"), formatter);
            visitLog.setEntryTime(Timestamp.valueOf(date));
            check = true;
        }
        if (params.get("entryTime") != null && !params.get("entryTime").isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime date = LocalDateTime.parse(params.get("dueDate"), formatter);
            visitLog.setEntryTime(Timestamp.valueOf(date));
            check = true;
        }

        if (params.get("status") != null && !params.get("status").isEmpty()) {
            visitLog.setStatus(params.get("status"));
            check = true;
        }

        if (check) {
            visitLog.setUpdatedAt(new Timestamp(new Date().getTime()));
            return visitLogRepo.save(visitLog);
        }

        return null;
    }
}
