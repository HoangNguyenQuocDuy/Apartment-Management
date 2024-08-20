package hnqd.project.ApartmentManagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "createdDate")
    private LocalDateTime createdDate;

    @Column(name = "postId")
    private Integer postId;

    @Column(name = "userId")
    private Integer userId;
}
