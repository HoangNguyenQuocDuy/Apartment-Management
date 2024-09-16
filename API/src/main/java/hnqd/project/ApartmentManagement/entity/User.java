package hnqd.project.ApartmentManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@Getter
@Setter
@Entity
public class User implements UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "firstname")
    private String firstname;
    @Basic
    @Column(name = "lastname")
    private String lastname;
    @Basic
    @Column(name = "resetPasswordCode")
    private String resetPasswordCode;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "avatar")
    private String avatar;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "roleName")
    private String roleName;

    @JsonIgnore
    @Transient
    private MultipartFile file;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Collection<Comment> comments;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Collection<Feedback> feedbacks;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Collection<Payment> payments;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Collection<Post> posts;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Collection<Relative> relatives;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Collection<Thread> threads;
    @ManyToOne
    @JoinColumn(name = "roomId", referencedColumnName = "id")
    private Room room;
    @ManyToOne
    @JoinColumn(name = "lockerId", referencedColumnName = "id")
    private Locker locker;

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

}
