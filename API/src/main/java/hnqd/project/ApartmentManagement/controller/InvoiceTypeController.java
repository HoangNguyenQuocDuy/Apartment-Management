package hnqd.project.ApartmentManagement.controller;

import hnqd.project.ApartmentManagement.dto.ResponseObject;
import hnqd.project.ApartmentManagement.service.IInvoiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/invoiceTypes")
public class InvoiceTypeController {

    @Autowired
    private IInvoiceTypeService invoiceTypeService;

    @GetMapping("/")
    public ResponseEntity<ResponseObject> getInvoiceTypes() {
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Get invoices type successfully!",
                        invoiceTypeService.getAllInvoiceTypes())
        );
    }
}
