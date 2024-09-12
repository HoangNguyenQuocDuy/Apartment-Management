package hnqd.project.ApartmentManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collection;

@Entity
public class Roomtype {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "type")
    private String type;
    @Basic
    @Column(name = "price")
    private BigDecimal price;
    @JsonIgnore
    @OneToMany(mappedBy = "roomTypeByRoomTypeId")
    private Collection<Room> roomsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Collection<Room> getRoomsById() {
        return roomsById;
    }

    public void setRoomsById(Collection<Room> roomsById) {
        this.roomsById = roomsById;
    }
}
