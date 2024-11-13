package hnqd.project.ApartmentManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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
    @ElementCollection
    @CollectionTable(name = "post_images", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "image_url")
    private List<String> postImageUrls;
    @Basic
    @Column(name = "likes")
    private long likes;
    @Basic
    @Column(name = "createdAt")
    private Timestamp createdAt;
    @Basic
    @Column(name = "updatedAt")
    private Timestamp updatedAt;
    @OneToMany(mappedBy = "post")
    private Collection<Comment> comments;
    @ManyToOne
    @JoinColumn(name = "threadId", referencedColumnName = "id", nullable = false)
    private Thread thread;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User user;
    @JsonIgnore
    @ManyToMany(mappedBy = "likedPosts", cascade = { CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY)
    private Set<User> likeByUsers;

    public Collection<Comment> setComments(Comment comment) {
        comments.add(comment);
        return comments;
    }
}
