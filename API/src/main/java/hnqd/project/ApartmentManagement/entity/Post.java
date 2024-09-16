package hnqd.project.ApartmentManagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Collection;

@Setter
@Getter
@Entity
public class Post {
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
    @OneToMany(mappedBy = "post")
    private Collection<Comment> comments;
    @ManyToOne
    @JoinColumn(name = "threadId", referencedColumnName = "id", nullable = false)
    private Thread thread;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User user;

}
