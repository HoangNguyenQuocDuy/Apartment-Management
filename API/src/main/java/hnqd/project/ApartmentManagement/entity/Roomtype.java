package hnqd.project.ApartmentManagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "roomtype")
public class Roomtype {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "price")
    private BigDecimal price;
}
