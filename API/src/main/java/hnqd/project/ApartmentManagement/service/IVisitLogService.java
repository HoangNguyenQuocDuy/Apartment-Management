package hnqd.project.ApartmentManagement.service;

import hnqd.project.ApartmentManagement.dto.VisitLogRequest;
import hnqd.project.ApartmentManagement.entity.VisitLog;

import java.util.List;
import java.util.Map;

public interface IVisitLogRepo {
    VisitLog createVisitLog(VisitLogRequest visitLogReq);
    List<VisitLog> getVisitLogs();
    VisitLog updateVisitLog(Map<String, String>);
}
