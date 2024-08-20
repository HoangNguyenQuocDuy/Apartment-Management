package hnqd.project.ApartmentManagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "visitlog")
public class Visitlog {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "entryTime")
    private LocalDateTime entryTime;

    @Column(name = "exitTime")
    private LocalDateTime exitTime;

    @Column(name = "visitorId")
    private Integer visitorId;

    @Column(name = "roomId")
    private Integer roomId;
}
