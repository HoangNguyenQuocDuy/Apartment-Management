package hnqd.project.ApartmentManagement.controller;

import hnqd.project.ApartmentManagement.dto.ResponseObject;
import hnqd.project.ApartmentManagement.dto.VisitLogRequest;
import hnqd.project.ApartmentManagement.dto.VisitRequest;
import hnqd.project.ApartmentManagement.dto.VisitorRequest;
import hnqd.project.ApartmentManagement.entity.User;
import hnqd.project.ApartmentManagement.entity.VisitLog;
import hnqd.project.ApartmentManagement.entity.Visitor;
import hnqd.project.ApartmentManagement.service.IVisitLogService;
import hnqd.project.ApartmentManagement.service.IVisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/visitors")
public class VisitorController {

    @Autowired
    private IVisitorService visitorService;
    @Autowired
    private IVisitLogService visitLogService;

    @PostMapping("/")
    public ResponseEntity<ResponseObject> createVisitor(@RequestBody VisitRequest visitReq){
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
                new ResponseObject("OK", "Create visitor successfully!", visitLog)
        );
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject> getVisitors(){
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Get visitors successfully!", visitorService.getVisitors())
        );
    }
}
