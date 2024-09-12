package hnqd.project.ApartmentManagement.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class UserResponse {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String resetPasswordCode;
    private String phone;
    private String roleName;
    private String status;

}
