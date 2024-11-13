package hnqd.project.ApartmentManagement.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentRequestDto {
    private String content;
    private int userId;
    private int postId;
}
