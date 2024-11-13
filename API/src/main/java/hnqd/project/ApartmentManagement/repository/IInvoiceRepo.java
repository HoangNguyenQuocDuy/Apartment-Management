package hnqd.project.ApartmentManagement.repository;

import hnqd.project.ApartmentManagement.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;

@Repository
public interface IInvoiceRepo extends JpaRepository<Invoice, Integer> {
    Page<Invoice> findAllByStatusAndRoomIdAndInvoiceTypeId(String status, int room_id, int invoiceType_id, Pageable page);
    Page<Invoice> findAllByStatusAndRoomId(String status, int room_id, Pageable page);
    Page<Invoice> findAllByStatus(String status, Pageable page);
    Page<Invoice> findAllByRoomId(int room_id, Pageable page);
    Page<Invoice> findAllByInvoiceTypeId(int invoiceType_id, Pageable page);
    Page<Invoice> findAllByStatusAndInvoiceTypeId(String status, int invoiceType_id, Pageable page);
    Page<Invoice> findAllByRoomIdAndInvoiceTypeId(int room_id, int invoiceType_id, Pageable page);
}
