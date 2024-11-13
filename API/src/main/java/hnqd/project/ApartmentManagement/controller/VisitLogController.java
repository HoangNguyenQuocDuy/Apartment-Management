package hnqd.project.ApartmentManagement.controller;

import hnqd.project.ApartmentManagement.dto.ResponseObject;
import hnqd.project.ApartmentManagement.dto.VisitLogRequest;
import hnqd.project.ApartmentManagement.dto.VisitRequest;
import hnqd.project.ApartmentManagement.dto.VisitorRequest;
import hnqd.project.ApartmentManagement.entity.VisitLog;
import hnqd.project.ApartmentManagement.entity.Visitor;
import hnqd.project.ApartmentManagement.service.IVisitLogService;
import hnqd.project.ApartmentManagement.service.IVisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/visitLogs")
public class VisitLogController {

    @Autowired
    private IVisitLogService visitLogService;
    @Autowired
    private IVisitorService visitorService;

    @PostMapping("/")
    public ResponseEntity<ResponseObject> createVisitLog(@RequestBody VisitRequest visitReq) {
        VisitorRequest visitorReq = new VisitorRequest();
        visitorReq.setVisitReason(visitReq.getVisitReason());
        visitorReq.setLastname(visitReq.getLastname());
        visitorReq.setFirstname(visitReq.getFirstname());
        visitorReq.setPhoneNumber(visitReq.getPhoneNumber());
        Visitor visitor = visitorService.createVisitor(visitorReq);

        VisitLogRequest visitLogReq = new VisitLogRequest();

        visitLogReq.setEntryTime(visitReq.getEntryTime());
        visitLogReq.setRoomId(visitReq.getRoomId());
        visitLogReq.setExitTime(visitReq.getExitTime());
        visitLogReq.setVisitorId(visitor.getId());

        VisitLog visitLog = visitLogService.createVisitLog(visitLogReq);

        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(
                new ResponseObject("OK", "Create visit's log successfully!", visitLog)
        );
    }

    @PutMapping("/{visitLogId}")
    public ResponseEntity<ResponseObject> updateVisitLog(
            @PathVariable("visitLogId") int visitLogId,
            @RequestBody Map<String, String> data
    ) {
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Update visit's log successfully!",
                        visitLogService.updateVisitLog(visitLogId, data))
        );
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject> getVisitLogs(
            @RequestParam Map<String, String> params
    ) {
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Get visit's logs successfully!",
                        visitLogService.getVisitLogs(params))
        );
    }
}
