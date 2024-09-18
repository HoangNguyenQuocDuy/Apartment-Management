package hnqd.project.ApartmentManagement.service;

import hnqd.project.ApartmentManagement.dto.VisitorRequest;
import hnqd.project.ApartmentManagement.entity.Visitor;

import java.util.List;

public interface IVisitorService {
    List<Visitor> getVisitors();
    Visitor createVisitor(VisitorRequest visitorReq);
}
