package hnqd.project.ApartmentManagement.service;

import hnqd.project.ApartmentManagement.dto.InvoiceRequest;
import hnqd.project.ApartmentManagement.entity.Invoice;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IInvoiceService {
    Invoice createInvoice(InvoiceRequest invoice);
    Page<Invoice> getInvoices(Map<String, String> params);
    Invoice updateInvoice(int invoiceId, Map<String, String> params);
    void deleteInvoice(int invoiceId);
}
