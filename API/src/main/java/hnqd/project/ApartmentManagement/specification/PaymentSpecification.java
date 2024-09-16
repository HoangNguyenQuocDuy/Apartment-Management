package hnqd.project.ApartmentManagement.specification;

import hnqd.project.ApartmentManagement.entity.Payment;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Timestamp;

public class PaymentSpecification {
    public static Specification<Payment> hasInvoiceId(int invoiceId) {
        return (Root<Payment> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.equal(root.get("invoice").get("id"), invoiceId);
    }

    public static Specification<Payment> hasUserId(int userId) {
        return (Root<Payment> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.equal(root.get("user").get("id"), userId);
    }

    public static Specification<Payment> createdAtBetween(Timestamp from, Timestamp to) {
        return (Root<Payment> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.between(root.get("createdAt"), from, to);
    }
}
