package hnqd.project.ApartmentManagement.service.Impl;

import hnqd.project.ApartmentManagement.entity.InvoiceType;
import hnqd.project.ApartmentManagement.repository.IInvoiceTypeRepo;
import hnqd.project.ApartmentManagement.service.IInvoiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceTypeServiceImpl implements IInvoiceTypeService {

    @Autowired
    private IInvoiceTypeRepo iInvoiceTypeRepo;

    @Override
    public List<InvoiceType> getAllInvoiceTypes() {
        return iInvoiceTypeRepo.findAll();
    }
}
