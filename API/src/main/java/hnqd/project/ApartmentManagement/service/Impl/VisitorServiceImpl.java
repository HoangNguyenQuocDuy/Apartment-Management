package hnqd.project.ApartmentManagement.service.Impl;

import hnqd.project.ApartmentManagement.dto.VisitorRequest;
import hnqd.project.ApartmentManagement.entity.Visitor;
import hnqd.project.ApartmentManagement.repository.IVisitorRepo;
import hnqd.project.ApartmentManagement.service.IVisitorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorServiceImpl implements IVisitorService {
    @Autowired
    private IVisitorRepo visitorRepo;

    @Override
    public List<Visitor> getVisitors() {
        return visitorRepo.findAll();
    }

    @Override
    @Transactional
    public Visitor createVisitor(VisitorRequest visitorReq) {
        Visitor visitor = new Visitor();
        visitor.setFirstname(visitorReq.getFirstname());
        visitor.setLastname(visitorReq.getLastname());
        visitor.setVisitReason(visitorReq.getVisitReason());
        visitor.setPhoneNumber(visitorReq.getPhoneNumber());

        return visitorRepo.saveAndFlush(visitor);
    }
}
