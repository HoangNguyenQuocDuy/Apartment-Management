package hnqd.project.ApartmentManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

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
    @OneToMany(mappedBy = "userByUserId")
    private Collection<Comment> commentsById;
    @JsonIgnore
    @OneToMany(mappedBy = "userByUserId")
    private Collection<Feedback> feedbacksById;
    @JsonIgnore
    @OneToMany(mappedBy = "userByUserId")
    private Collection<Payment> paymentsById;
    @JsonIgnore
    @OneToMany(mappedBy = "userByUserId")
    private Collection<Post> postsById;
    @JsonIgnore
    @OneToMany(mappedBy = "userByUserId")
    private Collection<Relative> relativesById;
    @JsonIgnore
    @OneToMany(mappedBy = "userByUserId")
    private Collection<Thread> threadsById;
    @ManyToOne
    @JoinColumn(name = "roomId", referencedColumnName = "id")
    private Room roomByRoomId;
    @ManyToOne
    @JoinColumn(name = "lockerId", referencedColumnName = "id")
    private Locker lockerByLockerId;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getResetPasswordCode() {
        return resetPasswordCode;
    }

    public void setResetPasswordCode(String resetPasswordCode) {
        this.resetPasswordCode = resetPasswordCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Collection<Comment> getCommentsById() {
        return commentsById;
    }

    public void setCommentsById(Collection<Comment> commentsById) {
        this.commentsById = commentsById;
    }

    public Collection<Feedback> getFeedbacksById() {
        return feedbacksById;
    }

    public void setFeedbacksById(Collection<Feedback> feedbacksById) {
        this.feedbacksById = feedbacksById;
    }

    public Collection<Payment> getPaymentsById() {
        return paymentsById;
    }

    public void setPaymentsById(Collection<Payment> paymentsById) {
        this.paymentsById = paymentsById;
    }

    public Collection<Post> getPostsById() {
        return postsById;
    }

    public void setPostsById(Collection<Post> postsById) {
        this.postsById = postsById;
    }

    public Collection<Relative> getRelativesById() {
        return relativesById;
    }

    public void setRelativesById(Collection<Relative> relativesById) {
        this.relativesById = relativesById;
    }

    public Collection<Thread> getThreadsById() {
        return threadsById;
    }

    public void setThreadsById(Collection<Thread> threadsById) {
        this.threadsById = threadsById;
    }

    public Room getRoomByRoomId() {
        return roomByRoomId;
    }

    public void setRoomByRoomId(Room roomByRoomId) {
        this.roomByRoomId = roomByRoomId;
    }

    public Locker getLockerByLockerId() {
        return lockerByLockerId;
    }

    public void setLockerByLockerId(Locker lockerByLockerId) {
        this.lockerByLockerId = lockerByLockerId;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }
}
