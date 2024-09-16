package hnqd.project.ApartmentManagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
public class ParkingRight {
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
    private Relative relative;
}
