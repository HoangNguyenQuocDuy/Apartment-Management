package hnqd.project.ApartmentManagement.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Entity
public class VisitLog {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "entryTime")
    private Timestamp entryTime;
    @Basic
    @Column(name = "exitTime")
    private Timestamp exitTime;
    @ManyToOne
    @JoinColumn(name = "visitorId", referencedColumnName = "id", nullable = false)
    private Visitor visitor;
    @ManyToOne
    @JoinColumn(name = "roomId", referencedColumnName = "id", nullable = false)
    private Room room;
}
