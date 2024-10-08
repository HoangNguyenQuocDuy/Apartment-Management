package hnqd.project.ApartmentManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Setter
@Getter
@Entity
public class Visitor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "firstname")
    private String firstname;
    @Basic
    @Column(name = "lastname")
    private String lastname;
    @Basic
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Basic
    @Column(name = "visitReason")
    private String visitReason;
    @JsonIgnore
    @OneToMany(mappedBy = "visitor")
    private Collection<VisitLog> visitLogs;
}
