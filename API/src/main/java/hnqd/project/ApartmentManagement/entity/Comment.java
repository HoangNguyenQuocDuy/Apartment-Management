package hnqd.project.ApartmentManagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@Entity
public class Comment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "content")
    private String content;
    @Basic
    @Column(name = "createdDate")
    private Timestamp createdDate;
    @ManyToOne
    @JoinColumn(name = "postId", referencedColumnName = "id", nullable = false)
    private Post post;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User user;

}
