package hnqd.project.ApartmentManagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "thread")
public class Thread {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "createdDate")
    private LocalDateTime createdDate;

    @Column(name = "userId")
    private Integer userId;
}
