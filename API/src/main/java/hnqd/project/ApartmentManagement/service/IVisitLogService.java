package hnqd.project.ApartmentManagement.service;

import hnqd.project.ApartmentManagement.dto.VisitLogRequest;
import hnqd.project.ApartmentManagement.entity.VisitLog;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IVisitLogService {
    VisitLog createVisitLog(VisitLogRequest visitLogReq);
    Page<VisitLog> getVisitLogs(Map<String, String> params);
    VisitLog updateVisitLog(int visitLogId, Map<String, String> params);
}
