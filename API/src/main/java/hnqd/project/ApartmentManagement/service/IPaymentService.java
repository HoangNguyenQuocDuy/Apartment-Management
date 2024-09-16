package hnqd.project.ApartmentManagement.service;

import hnqd.project.ApartmentManagement.entity.Payment;

import java.util.List;
import java.util.Map;

public interface IPaymentService {
    Payment createPayment(Payment payment);
    List<Payment> getPayments(Map<String, String> params);
}
