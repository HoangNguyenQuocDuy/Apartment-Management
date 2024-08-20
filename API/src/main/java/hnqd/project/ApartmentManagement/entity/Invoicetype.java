package hnqd.project.ApartmentManagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "invoicetype")
public class Invoicetype {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    private String type;
}
