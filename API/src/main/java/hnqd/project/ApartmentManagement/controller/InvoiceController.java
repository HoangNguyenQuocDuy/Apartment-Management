package hnqd.project.ApartmentManagement.controller;

import hnqd.project.ApartmentManagement.dto.InvoiceRequest;
import hnqd.project.ApartmentManagement.dto.ResponseObject;
import hnqd.project.ApartmentManagement.service.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    @Autowired
    private IInvoiceService invoiceService;

    @GetMapping("/")
    public ResponseEntity<ResponseObject> getInvoices(@RequestParam Map<String, String> params) {
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Get invoices successfully!",
                        invoiceService.getInvoices(params))
        );
    }

    @PostMapping("/")
    public ResponseEntity<?> createInvoice(@RequestBody InvoiceRequest invoiceRequest) {
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(
                new ResponseObject("OK", "Create invoice successfully!",
                        invoiceService.createInvoice(invoiceRequest))
        );
    }

    @PutMapping("/{invoiceId}")
    public ResponseEntity<?> updateEntryRight(@RequestBody Map<String, String> data,
                                              @PathVariable("invoiceId") int invoiceId) {
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Update invoice successfully!",
                        invoiceService.updateInvoice(invoiceId, data)
                )
        );
    }
}
