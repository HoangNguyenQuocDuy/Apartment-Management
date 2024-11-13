package hnqd.project.ApartmentManagement.service;

import hnqd.project.ApartmentManagement.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IPaymentService {
    Payment createPayment(Payment payment);
    Payment createPaymentManual(MultipartFile file, Map<String, String> params) throws IOException;
    Page<Payment> getPayments(Map<String, String> params);
}
