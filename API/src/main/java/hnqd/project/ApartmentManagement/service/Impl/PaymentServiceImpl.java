package hnqd.project.ApartmentManagement.service.Impl;

import hnqd.project.ApartmentManagement.entity.Invoice;
import hnqd.project.ApartmentManagement.entity.Payment;
import hnqd.project.ApartmentManagement.exceptions.CommonException;
import hnqd.project.ApartmentManagement.repository.IInvoiceRepo;
import hnqd.project.ApartmentManagement.repository.IPaymentRepo;
import hnqd.project.ApartmentManagement.repository.IUserRepo;
import hnqd.project.ApartmentManagement.service.IPaymentService;
import hnqd.project.ApartmentManagement.specification.PaymentSpecification;
import hnqd.project.ApartmentManagement.utils.UploadImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements IPaymentService {
    @Autowired
    private IPaymentRepo paymentRepo;
    @Autowired
    private UploadImage uploadImage;
    @Autowired
    private IInvoiceRepo invoiceRepo;
    @Autowired
    private IUserRepo userRepo;

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepo.save(payment);
    }

    @Override
    public Payment createPaymentManual(MultipartFile file, Map<String, String> params) throws IOException {
        Invoice invoiceSave = invoiceRepo.findById(Integer.parseInt(params.get("invoiceId"))).orElseThrow(
                () -> new CommonException.NotFoundException("Invoice not found!")
        );

        Payment payment = new Payment();
        if (file != null) {
            payment.setUploadImage(uploadImage.uploadToCloudinary(file));
        }
        payment.setInvoice(invoiceSave);
        payment.setAmount(invoiceSave.getAmount());
        payment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        payment.setUser(userRepo.findById(Integer.parseInt(params.get("userId"))).orElseThrow(
                () -> new CommonException.NotFoundException("User not found!")
        ));
        invoiceSave.setStatus("Pending");
        invoiceRepo.save(invoiceSave);
        return paymentRepo.save(payment);
    }

    @Override
    public Page<Payment> getPayments(Map<String, String> params) {
        String from = params.getOrDefault("from", null);
        String to = params.getOrDefault("to", null);
        int userId = Integer.parseInt(params.getOrDefault("userId", "0"));
        int invoiceId = Integer.parseInt(params.getOrDefault("invoiceId", "0"));
        int page = Integer.parseInt(params.getOrDefault("page", "0"));  // Trang mặc định là 0
        int size = Integer.parseInt(params.getOrDefault("size", "10")); // Số bản ghi mặc định là 10
        Pageable pageable = PageRequest.of(page, size);

        Specification<Payment> spec = Specification.where(null);
        boolean flag = false;

        if (from != null && to != null) {
            spec = spec.and(PaymentSpecification.createdAtBetween(parseTimestamp(from), parseTimestamp(to)));
            flag = true;
        } else if (from != null) {
            spec = spec.and(PaymentSpecification.createdAtBetween(parseTimestamp(from), new Timestamp(new Date().getTime())));
            flag = true;
        }

        if (userId != 0) {
            spec = spec.and(PaymentSpecification.hasUserId(userId));
            flag = true;
        }

        if (invoiceId != 0) {
            spec = spec.and(PaymentSpecification.hasInvoiceId(invoiceId));
            flag = true;
        }

        if (flag) {
            return paymentRepo.findAll(spec, pageable);
        } else {
            return paymentRepo.findAll(pageable);
        }
    }

    private Timestamp parseTimestamp(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);

        return Timestamp.valueOf(localDateTime);
    }
}
