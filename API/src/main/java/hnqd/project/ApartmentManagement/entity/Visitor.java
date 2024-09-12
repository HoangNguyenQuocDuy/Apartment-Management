package hnqd.project.ApartmentManagement.entity;

import jakarta.persistence.*;

import java.util.Collection;

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
    @OneToMany(mappedBy = "visitorByVisitorId")
    private Collection<Visitlog> visitlogsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVisitReason() {
        return visitReason;
    }

    public void setVisitReason(String visitReason) {
        this.visitReason = visitReason;
    }

    public Collection<Visitlog> getVisitlogsById() {
        return visitlogsById;
    }

    public void setVisitlogsById(Collection<Visitlog> visitlogsById) {
        this.visitlogsById = visitlogsById;
    }
}
