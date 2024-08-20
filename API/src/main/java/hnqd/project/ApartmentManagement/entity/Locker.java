package hnqd.project.ApartmentManagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "locker")
public class Locker {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "status")
    private String status;
}
