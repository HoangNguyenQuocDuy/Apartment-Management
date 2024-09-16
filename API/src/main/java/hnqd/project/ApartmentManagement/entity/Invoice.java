package hnqd.project.ApartmentManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@Setter
@Getter
@Entity
public class Invoice {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "amount")
    private BigDecimal amount;
    @Basic
    @Column(name = "dueDate")
    private LocalDateTime dueDate;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "createdAt")
    private Timestamp createdAt;
    @Basic
    @Column(name = "updatedAt")
    private Timestamp updatedAt;
    @ManyToOne
    @JoinColumn(name = "roomId", referencedColumnName = "id", nullable = false)
    private Room room;
    @ManyToOne
    @JoinColumn(name = "invoiceTypeId", referencedColumnName = "id", nullable = false)
    private InvoiceType invoiceType;
    @JsonIgnore
    @OneToMany(mappedBy = "invoice")
    private Collection<Payment> payments;

}
