package hnqd.project.ApartmentManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Entity
public class Room {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "image")
    private String image;
    @JsonIgnore
    @OneToMany(mappedBy = "room")
    private Collection<Invoice> invoices;
    @ManyToOne
    @JoinColumn(name = "roomTypeId", referencedColumnName = "id", nullable = false)
    private RoomType roomType;
    @JsonIgnore
    @OneToMany(mappedBy = "room")
    private Collection<User> users;
    @JsonIgnore
    @OneToMany(mappedBy = "room")
    private Collection<VisitLog> visitLogs;
}
