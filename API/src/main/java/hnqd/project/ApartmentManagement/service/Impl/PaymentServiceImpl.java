package hnqd.project.ApartmentManagement.service.Impl;

import hnqd.project.ApartmentManagement.entity.Payment;
import hnqd.project.ApartmentManagement.repository.IPaymentRepo;
import hnqd.project.ApartmentManagement.service.IPaymentService;
import hnqd.project.ApartmentManagement.specification.PaymentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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


    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepo.save(payment);
    }

    @Override
    public List<Payment> getPayments(Map<String, String> params) {
        Timestamp from = parseTimestamp(params.getOrDefault("from", null));
        Timestamp to = parseTimestamp(params.getOrDefault("to", null));
        int userId = Integer.parseInt(params.getOrDefault("userId", "0"));
        int invoiceId = Integer.parseInt(params.getOrDefault("invoiceId", "0"));

        Specification<Payment> spec = Specification.where(null);

        if (from != null && to != null) {
            spec = spec.and(PaymentSpecification.createdAtBetween(from, to));
        }

        if (userId != 0) {
            spec = spec.and(PaymentSpecification.hasUserId(userId));
        }

        if (invoiceId != 0) {
            spec = spec.and(PaymentSpecification.hasInvoiceId(invoiceId));
        }

        return paymentRepo.findAll(spec);
    }

    private Timestamp parseTimestamp(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);

        return Timestamp.valueOf(localDateTime);
    }
}
