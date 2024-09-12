package hnqd.project.ApartmentManagement.dto;

import hnqd.project.ApartmentManagement.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private UserResponse userResponse;
    private String accessToken;
}
