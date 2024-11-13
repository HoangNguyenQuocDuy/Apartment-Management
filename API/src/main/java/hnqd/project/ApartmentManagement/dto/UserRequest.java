package hnqd.project.ApartmentManagement.dto;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
public class UserRequest {
    private String username;
    private String firstname;
    private String lastname;
    @Nullable
    private String password;
    private String phone;
    private String email;
    private String status;
    private Integer lockerId;
    private Integer roomId;
    @Nullable
    private MultipartFile file;
}
