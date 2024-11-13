package hnqd.project.ApartmentManagement.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
public class PostRequest {
    private String title;
    private int userId;
    private int threadId;
    private MultipartFile[] files;
}
