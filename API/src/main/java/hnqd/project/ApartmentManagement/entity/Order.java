package hnqd.project.ApartmentManagement.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity(name = "UserOrder")
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "image")
    private String image;
    @Basic
    @Column(name = "createdAt")
    private Timestamp createdAt;
    @Basic
    @Column(name = "updatedAt")
    private Timestamp updatedAt;
    @ManyToOne
    @JoinColumn(name = "lockerId", referencedColumnName = "id", nullable = false)
    private Locker lockerByLockerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Locker getLockerByLockerId() {
        return lockerByLockerId;
    }

    public void setLockerByLockerId(Locker lockerByLockerId) {
        this.lockerByLockerId = lockerByLockerId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {}
}
