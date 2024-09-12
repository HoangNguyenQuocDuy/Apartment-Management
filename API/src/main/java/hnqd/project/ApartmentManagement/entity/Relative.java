package hnqd.project.ApartmentManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@Entity
public class Relative {
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
    @Column(name = "avatar")
    private String avatar;
    @Basic
    @Column(name = "type")
    private String type;
    @OneToMany(mappedBy = "relativeByRelativeId")
    private Collection<Entryright> entryrightsById;
    @OneToMany(mappedBy = "relativeByRelativeId")
    private Collection<Parkingright> parkingrightsById;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User userByUserId;

    @JsonIgnore
    @Transient
    private MultipartFile file;

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Collection<Entryright> getEntryrightsById() {
        return entryrightsById;
    }

    public void setEntryrightsById(Collection<Entryright> entryrightsById) {
        this.entryrightsById = entryrightsById;
    }

    public Collection<Parkingright> getParkingrightsById() {
        return parkingrightsById;
    }

    public void setParkingrightsById(Collection<Parkingright> parkingrightsById) {
        this.parkingrightsById = parkingrightsById;
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
