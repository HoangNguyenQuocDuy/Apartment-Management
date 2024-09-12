package hnqd.project.ApartmentManagement.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

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
    private Date dueDate;
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
    private Room roomByRoomId;
    @ManyToOne
    @JoinColumn(name = "invoiceTypeId", referencedColumnName = "id", nullable = false)
    private Invoicetype invoicetypeByInvoiceTypeId;
    @OneToMany(mappedBy = "invoiceByInvoiceId")
    private Collection<Payment> paymentsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Room getRoomByRoomId() {
        return roomByRoomId;
    }

    public void setRoomByRoomId(Room roomByRoomId) {
        this.roomByRoomId = roomByRoomId;
    }

    public Invoicetype getInvoicetypeByInvoiceTypeId() {
        return invoicetypeByInvoiceTypeId;
    }

    public void setInvoicetypeByInvoiceTypeId(Invoicetype invoicetypeByInvoiceTypeId) {
        this.invoicetypeByInvoiceTypeId = invoicetypeByInvoiceTypeId;
    }

    public Collection<Payment> getPaymentsById() {
        return paymentsById;
    }

    public void setPaymentsById(Collection<Payment> paymentsById) {
        this.paymentsById = paymentsById;
    }
}
