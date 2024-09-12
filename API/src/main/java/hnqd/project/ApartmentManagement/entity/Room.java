package hnqd.project.ApartmentManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

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
    @OneToMany(mappedBy = "roomByRoomId")
    private Collection<Invoice> invoicesById;
    @ManyToOne
    @JoinColumn(name = "roomTypeId", referencedColumnName = "id", nullable = false)
    private Roomtype roomTypeByRoomTypeId;
    @JsonIgnore
    @OneToMany(mappedBy = "roomByRoomId")
    private Collection<User> usersById;
    @JsonIgnore
    @OneToMany(mappedBy = "roomByRoomId")
    private Collection<Visitlog> visitLogsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Collection<Invoice> getInvoicesById() {
        return invoicesById;
    }

    public void setInvoicesById(Collection<Invoice> invoicesById) {
        this.invoicesById = invoicesById;
    }

    public Roomtype getRoomTypeByRoomTypeId() {
        return roomTypeByRoomTypeId;
    }

    public void setRoomTypeByRoomTypeId(Roomtype roomtypeByRoomTypeId) {
        this.roomTypeByRoomTypeId = roomtypeByRoomTypeId;
    }

    public Collection<User> getUsersById() {
        return usersById;
    }

    public void setUsersById(Collection<User> usersById) {
        this.usersById = usersById;
    }

    public Collection<Visitlog> getVisitLogsById() {
        return visitLogsById;
    }

    public void setVisitLogsById(Collection<Visitlog> visitlogsById) {
        this.visitLogsById = visitlogsById;
    }

}
