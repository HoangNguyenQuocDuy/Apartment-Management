package hnqd.project.ApartmentManagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "parkingright")
public class Parkingright {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "typeOfVehicle")
    private String typeOfVehicle;

    @Column(name = "licensePlates")
    private String licensePlates;

    @Column(name = "status")
    private String status;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @Column(name = "relativeId")
    private Integer relativeId;
}
