package hnqd.project.ApartmentManagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "relative")
public class Relative {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "type")
    private String type;

    @Column(name = "userId")
    private Integer userId;
}
