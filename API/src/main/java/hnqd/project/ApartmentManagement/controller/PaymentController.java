package hnqd.project.ApartmentManagement.controller;

import hnqd.project.ApartmentManagement.config.ConfigVNPay;
import hnqd.project.ApartmentManagement.dto.PaymentResponse;
import hnqd.project.ApartmentManagement.entity.Invoice;
import hnqd.project.ApartmentManagement.entity.Payment;
import hnqd.project.ApartmentManagement.entity.User;
import hnqd.project.ApartmentManagement.service.IInvoiceService;
import hnqd.project.ApartmentManagement.service.IPaymentService;
import hnqd.project.ApartmentManagement.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private IPaymentService paymentService;
    @Autowired
    private IInvoiceService invoiceService;
    @Autowired
    private IUserService userService;

    @GetMapping("/")
    ResponseEntity<?> createPage(HttpServletRequest req) throws UnsupportedEncodingException {
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String vnp_OrderInfo = "Noi dung thanh toan";
        String orderType = "order_type";
        String vnp_TxnRef = ConfigVNPay.getRandomNumber(8);
        String vnp_IpAddr = ConfigVNPay.getIpAddress(req);
        String vnp_TmnCode = ConfigVNPay.vnp_TmnCode;

        int amount = Integer.parseInt(req.getParameter("amount"));
        Map vnp_Params = new HashMap();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount * 100));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", "NCB");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", ConfigVNPay.vnp_Returnurl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());

        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        //Add Params of 2.1.0 Version
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        String callbackUrl = "http://localhost:8080/api/invoices/paySuccess/"
                + req.getParameter("userId") + "/" + req.getParameter("invoicerentId");
        vnp_Params.put("vnp_ReturnUrl", callbackUrl);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = ConfigVNPay.hmacSHA512(ConfigVNPay.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = ConfigVNPay.vnp_PayUrl + "?" + queryUrl;

        PaymentResponse paymentRes = PaymentResponse.builder()
                .status("OK")
                .message("Successfully")
                .URL(paymentUrl)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(paymentRes);
    }

    @GetMapping("/callback/{userId}/{invoicerentId}")
    public ResponseEntity<?> processPaymentCallback(HttpServletRequest req,
                                                    @PathVariable("invoicerentId") int invoicerentId,
                                                    @PathVariable("userId") int userId) {
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

    @GetMapping("/list")
    public ResponseEntity<?> getPayments(@RequestParam Map<String, String> params) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.getPayments(params));
    }

    @PostMapping("/manual")
    public ResponseEntity<?> createPayment(
            @RequestPart MultipartFile file,
            @RequestParam Map<String, String> params) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.createPaymentManual(file, params));
    }
}
