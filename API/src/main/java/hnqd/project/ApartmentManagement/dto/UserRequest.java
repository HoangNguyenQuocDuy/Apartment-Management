package hnqd.project.ApartmentManagement.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
public class UserRequest {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private String status;
    private MultipartFile file;
    private Integer lockerId;
    private Integer roomId;
}
