package hnqd.project.ApartmentManagement.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Setter
@Getter
@Entity
public class Payment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "amount")
    private BigDecimal amount;
    @Basic
    @Column(name = "bankCode")
    private String bankCode;
    @Basic
    @Column(name = "bankTranNo")
    private String bankTranNo;
    @Basic
    @Column(name = "transactionNo")
    private String transactionNo;
    @Basic
    @Column(name = "createdAt")
    private Timestamp createdAt;
    @Basic
    @Nullable
    @Column(name = "uploadImage")
    private String uploadImage;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "invoiceId", referencedColumnName = "id", nullable = false)
    private Invoice invoice;
}
