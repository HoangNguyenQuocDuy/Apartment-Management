package hnqd.project.ApartmentManagement.dto;

import hnqd.project.ApartmentManagement.entity.Locker;
import hnqd.project.ApartmentManagement.entity.Room;
import lombok.*;

@Getter
@Setter
@Builder
public class UserResponse {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String resetPasswordCode;
    private String phone;
    private String roleName;
    private String status;
    private Locker locker;
    private Room room;
}
