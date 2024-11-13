package hnqd.project.ApartmentManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@Setter
@Getter
@Entity
public class Relative {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "firstname")
    private String firstname;
    @Basic
    @Column(name = "lastname")
    private String lastname;
    @Basic
    @Column(name = "avatar")
    private String avatar;
    @Basic
    @Column(name = "type")
    private String type;
    @JsonIgnore
    @OneToMany(mappedBy = "relative")
    private Collection<EntryRight> entryRights;
    @JsonIgnore
    @OneToMany(mappedBy = "relative")
    private Collection<ParkingRight> parkingRights;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User user;

    @JsonIgnore
    @Transient
    private MultipartFile file;

}
