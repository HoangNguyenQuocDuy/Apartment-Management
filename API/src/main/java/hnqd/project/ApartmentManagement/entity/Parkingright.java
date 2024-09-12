package hnqd.project.ApartmentManagement.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Parkingright {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "typeOfVehicle")
    private String typeOfVehicle;
    @Basic
    @Column(name = "licensePlates")
    private String licensePlates;
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
    @JoinColumn(name = "relativeId", referencedColumnName = "id", nullable = false)
    private Relative relativeByRelativeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeOfVehicle() {
        return typeOfVehicle;
    }

    public void setTypeOfVehicle(String typeOfVehicle) {
        this.typeOfVehicle = typeOfVehicle;
    }

    public String getLicensePlates() {
        return licensePlates;
    }

    public void setLicensePlates(String licensePlates) {
        this.licensePlates = licensePlates;
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

    public Relative getRelativeByRelativeId() {
        return relativeByRelativeId;
    }

    public void setRelativeByRelativeId(Relative relativeByRelativeId) {
        this.relativeByRelativeId = relativeByRelativeId;
    }
}
