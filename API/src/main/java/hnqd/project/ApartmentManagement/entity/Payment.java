package hnqd.project.ApartmentManagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "bankCode")
    private String bankCode;

    @Column(name = "bankTranNo")
    private String bankTranNo;

    @Column(name = "transactionNo")
    private String transactionNo;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "invoiceId")
    private Integer invoiceId;
}
