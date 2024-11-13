package hnqd.project.ApartmentManagement.controller;

import hnqd.project.ApartmentManagement.entity.Invoice;
import hnqd.project.ApartmentManagement.entity.Payment;
import hnqd.project.ApartmentManagement.entity.User;
import hnqd.project.ApartmentManagement.service.IInvoiceService;
import hnqd.project.ApartmentManagement.service.IPaymentService;
import hnqd.project.ApartmentManagement.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cl")
public class VNCallbackController {
    @Autowired
    private IPaymentService paymentService;
    @Autowired
    private IInvoiceService invoiceService;
    @Autowired
    private IUserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> processPaymentCallback(HttpServletRequest req,
                                                    @PathVariable("userId") int userId) {
        System.out.println(userId);
//        if (req.getParameter("vnp_ResponseCode").equals("00")) {
//            Payment payment = new Payment();
//            payment.setAmount(BigDecimal.valueOf(Long.parseLong(req.getParameter("vnp_Amount"))));
//            payment.setBankCode(req.getParameter("vnp_BankCode"));
//            payment.setBankTranNo(req.getParameter("vnp_BankTranNo"));
//            payment.setTransactionNo(req.getParameter("vnp_TransactionNo"));
//            payment.setCreatedAt(new Timestamp(new Date().getTime()));
//
//            //update invoice
//            Map<String, String> map = new HashMap<>();
//            map.put("status", "Paid");
////            Invoice invoice = invoiceService.updateInvoice(invoicerentId, map);
//
//            User user = userService.getUserById(userId);
//
////            payment.setInvoice(invoice);
//            payment.setUser(user);
//
//            paymentService.createPayment(payment);
//        }

        return ResponseEntity.ok().build();
    }
}
