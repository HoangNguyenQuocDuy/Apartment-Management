package hnqd.project.ApartmentManagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "resetPasswordCode")
    private Byte resetPasswordCode;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "status")
    private String status;

    @Column(name = "roleName")
    private String roleName;

    @Column(name = "roomId")
    private Integer roomId;

    @Column(name = "lockerId")
    private Integer lockerId;
}
