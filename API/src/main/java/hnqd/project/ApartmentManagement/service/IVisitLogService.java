package hnqd.project.ApartmentManagement.service;

import hnqd.project.ApartmentManagement.dto.VisitLogRequest;
import hnqd.project.ApartmentManagement.entity.VisitLog;

import java.util.List;
import java.util.Map;

public interface IVisitLogService {
    VisitLog createVisitLog(VisitLogRequest visitLogReq);
    List<VisitLog> getVisitLogs();
    VisitLog updateVisitLog(int visitLogId, Map<String, String> params);
}
