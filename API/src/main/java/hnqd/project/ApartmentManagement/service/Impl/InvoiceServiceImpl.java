package hnqd.project.ApartmentManagement.service.Impl;

import hnqd.project.ApartmentManagement.dto.InvoiceRequest;
import hnqd.project.ApartmentManagement.entity.Invoice;
import hnqd.project.ApartmentManagement.entity.InvoiceType;
import hnqd.project.ApartmentManagement.entity.Room;
import hnqd.project.ApartmentManagement.exceptions.CommonException;
import hnqd.project.ApartmentManagement.repository.IInvoiceRepo;
import hnqd.project.ApartmentManagement.repository.IInvoiceTypeRepo;
import hnqd.project.ApartmentManagement.repository.IRoomRepo;
import hnqd.project.ApartmentManagement.service.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Service
public class InvoiceServiceImpl implements IInvoiceService {

    @Autowired
    private IInvoiceRepo invoiceRepo;
    @Autowired
    private IRoomRepo roomRepo;
    @Autowired
    private IInvoiceTypeRepo invoiceTypeRepo;


    @Override
    public Invoice createInvoice(InvoiceRequest invoiceReq) {
        if (Integer.parseInt(invoiceReq.getAmount().toString()) <= 0) {
            throw new CommonException.RequestBodyInvalid("Amount <= 0!!!");
        }
        InvoiceType invoiceType = invoiceTypeRepo.findById(invoiceReq.getInvoiceTypeId())
                .orElseThrow(() -> (
                        new CommonException.NotFoundException("Invoice type not found")
                ));

        Room room = roomRepo.findById(invoiceReq.getRoomId()).orElseThrow(
                () -> (new CommonException.NotFoundException("Room not found!"))
        );

        if (room.getStatus().equals("Available")) {
            throw new CommonException.RoomHasNotUsed("Room hasn't used!");
        }

        Invoice invoice = new Invoice();

        if (invoiceReq.getDueDate() == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, 5);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 0);

            invoice.setDueDate(new Timestamp(new Date(calendar.getTimeInMillis()).getTime()).toLocalDateTime());
        } else if (invoiceReq.getDueDate().isBefore(LocalDateTime.now()) || invoiceReq.getDueDate().isEqual(LocalDateTime.now())) {
            throw new CommonException.DueDateException("Due date cannot be in the past or equal to the current date/time.   ");
        } else {
            invoice.setDueDate(invoiceReq.getDueDate());
        }

        invoice.setDescription(invoiceReq.getDescription());
        invoice.setAmount(invoiceReq.getAmount());
        invoice.setStatus("UnPaid");
        invoice.setCreatedAt(new Timestamp(new Date().getTime()));
        invoice.setInvoiceType(invoiceType);
        invoice.setRoom(room);

        return invoiceRepo.save(invoice);
    }

    @Override
    public Page<Invoice> getInvoices(Map<String, String> params) {
        int page = Integer.parseInt(params.get("page"));
        int size = Integer.parseInt(params.get("size"));
        Pageable pageable = PageRequest.of(page, size);
        String status = params.getOrDefault("status", "");
        int roomId = Integer.parseInt(params.getOrDefault("roomId", "0"));
        int invoiceTypeId = Integer.parseInt(params.getOrDefault("invoiceTypeId", "0"));

        if (!status.isEmpty() && roomId != 0 && invoiceTypeId != 0) {
            return invoiceRepo.findAllByStatusAndRoomIdAndInvoiceTypeId(status, roomId, invoiceTypeId, pageable);
        } else if (status.isEmpty() && roomId != 0 && invoiceTypeId != 0) {
            return invoiceRepo.findAllByRoomIdAndInvoiceTypeId(roomId, roomId, pageable);
        } else if (status.isEmpty() && roomId == 0 && invoiceTypeId != 0) {
            return invoiceRepo.findAllByInvoiceTypeId(invoiceTypeId, pageable);
        } else if (!status.isEmpty() && roomId == 0 && invoiceTypeId == 0) {
            return invoiceRepo.findAllByStatus(status, pageable);
        } else if (status.isEmpty() && roomId != 0) {
            return invoiceRepo.findAllByRoomId(roomId, pageable);
        } else if (!status.isEmpty() && roomId != 0) {
            return invoiceRepo.findAllByStatusAndRoomId(status, roomId, pageable);
        } else if (!status.isEmpty()) {
            return invoiceRepo.findAllByStatusAndInvoiceTypeId(status, invoiceTypeId, pageable);
        } else {
            return invoiceRepo.findAll(pageable);
        }
    }

    @Override
    public Invoice updateInvoice(int invoiceId, Map<String, String> params) {
        Invoice invoiceSave = invoiceRepo.findById(invoiceId).orElseThrow(
                () -> new CommonException.NotFoundException("Invoice not found!")
        );

        boolean check = false;

        if (params.get("status") != null && !params.get("status").isEmpty()) {
            invoiceSave.setStatus(params.get("status"));
            check = true;
        }
        if (params.get("description") != null && !params.get("description").isEmpty()) {
            invoiceSave.setDescription(params.get("description"));
            check = true;
        }
        if (params.get("amount") != null && !params.get("amount").isEmpty()) {
            invoiceSave.setAmount(new BigDecimal(params.get("amount")));
            check = true;
        }
        if (params.get("dueDate") != null && !params.get("dueDate").isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime date = LocalDateTime.parse(params.get("dueDate"), formatter);
            invoiceSave.setDueDate(date);
            check = true;
        }

        if (check) {
            invoiceSave.setUpdatedAt(new Timestamp(new Date().getTime()));
            return invoiceRepo.save(invoiceSave);
        } else {
            throw new CommonException.RequestBodyInvalid("No keys match with invoice column");
        }
    }

    @Override
    public void deleteInvoice(int invoiceId) {
        invoiceRepo.deleteById(invoiceId);
    }
}
