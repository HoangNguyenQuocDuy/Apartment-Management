package hnqd.project.ApartmentManagement.controller;

import hnqd.project.ApartmentManagement.dto.InvoiceRequest;
import hnqd.project.ApartmentManagement.dto.ResponseObject;
import hnqd.project.ApartmentManagement.entity.Invoice;
import hnqd.project.ApartmentManagement.entity.Payment;
import hnqd.project.ApartmentManagement.entity.User;
import hnqd.project.ApartmentManagement.service.IInvoiceService;
import hnqd.project.ApartmentManagement.service.IPaymentService;
import hnqd.project.ApartmentManagement.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    @Autowired
    private IInvoiceService invoiceService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IPaymentService paymentService;

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

    @DeleteMapping("/{invoiceId}")
    public ResponseEntity<?> deleteInvoice(@PathVariable("invoiceId") Integer invoiceId) {
        invoiceService.deleteInvoice(invoiceId);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(
                new ResponseObject("OK", "Create invoice successfully!","")
        );
    }

    @GetMapping("/paySuccess/{userId}/{invoicerentId}")
    public ResponseEntity<?> processPaymentCallback(HttpServletRequest req,
                                                    @PathVariable("invoicerentId") int invoicerentId,
                                                    @PathVariable("userId") int userId) {
        System.out.println(invoicerentId);
        if (req.getParameter("vnp_ResponseCode").equals("00")) {
            Payment payment = new Payment();
            payment.setAmount(BigDecimal.valueOf(Long.parseLong(req.getParameter("vnp_Amount"))));
            payment.setBankCode(req.getParameter("vnp_BankCode"));
            payment.setBankTranNo(req.getParameter("vnp_BankTranNo"));
            payment.setTransactionNo(req.getParameter("vnp_TransactionNo"));
            payment.setCreatedAt(new Timestamp(new Date().getTime()));

            //update invoice
            Map<String, String> map = new HashMap<>();
            map.put("status", "Paid");
            Invoice invoice = invoiceService.updateInvoice(invoicerentId, map);

            User user = userService.getUserById(userId);

            payment.setInvoice(invoice);
            payment.setUser(user);

            paymentService.createPayment(payment);
        }

        return ResponseEntity.ok().build();
    }
}
