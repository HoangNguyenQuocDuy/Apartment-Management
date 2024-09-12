package hnqd.project.ApartmentManagement.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;

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
    @OneToMany(mappedBy = "postByPostId")
    private Collection<Comment> commentsById;
    @ManyToOne
    @JoinColumn(name = "threadId", referencedColumnName = "id", nullable = false)
    private Thread threadByThreadId;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User userByUserId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Collection<Comment> getCommentsById() {
        return commentsById;
    }

    public void setCommentsById(Collection<Comment> commentsById) {
        this.commentsById = commentsById;
    }

    public Thread getThreadByThreadId() {
        return threadByThreadId;
    }

    public void setThreadByThreadId(Thread threadByThreadId) {
        this.threadByThreadId = threadByThreadId;
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
