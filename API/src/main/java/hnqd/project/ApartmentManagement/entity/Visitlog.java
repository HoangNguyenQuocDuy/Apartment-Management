package hnqd.project.ApartmentManagement.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Visitlog {
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
    private Visitor visitorByVisitorId;
    @ManyToOne
    @JoinColumn(name = "roomId", referencedColumnName = "id", nullable = false)
    private Room roomByRoomId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Timestamp entryTime) {
        this.entryTime = entryTime;
    }

    public Timestamp getExitTime() {
        return exitTime;
    }

    public void setExitTime(Timestamp exitTime) {
        this.exitTime = exitTime;
    }

    public Visitor getVisitorByVisitorId() {
        return visitorByVisitorId;
    }

    public void setVisitorByVisitorId(Visitor visitorByVisitorId) {
        this.visitorByVisitorId = visitorByVisitorId;
    }

    public Room getRoomByRoomId() {
        return roomByRoomId;
    }

    public void setRoomByRoomId(Room roomByRoomId) {
        this.roomByRoomId = roomByRoomId;
    }
}
