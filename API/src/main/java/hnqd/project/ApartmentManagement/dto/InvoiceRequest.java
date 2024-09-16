package hnqd.project.ApartmentManagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class InvoiceRequest {
    private String description;
    private BigDecimal amount;
    private LocalDateTime dueDate;
    private int roomId;
    private int invoiceTypeId;
}
