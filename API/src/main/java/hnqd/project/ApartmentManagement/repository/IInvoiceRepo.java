package hnqd.project.ApartmentManagement.repository;

import hnqd.project.ApartmentManagement.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInvoiceRepo extends JpaRepository<Invoice, Integer> {
    List<Invoice> findAllByStatusAndRoomIdAndInvoiceTypeId(String status, int room_id, int invoiceType_id);
    List<Invoice> findAllByStatusAndRoomId(String status, int room_id);
    List<Invoice> findAllByStatus(String status);
    List<Invoice> findAllByRoomId(int room_id);
    List<Invoice> findAllByInvoiceTypeId(int invoiceType_id);
    List<Invoice> findAllByStatusAndInvoiceTypeId(String status, int invoiceType_id);
    List<Invoice> findAllByRoomIdAndInvoiceTypeId(int room_id, int invoiceType_id);
}
