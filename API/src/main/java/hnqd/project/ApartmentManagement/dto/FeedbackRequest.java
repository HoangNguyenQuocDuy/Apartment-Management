package hnqd.project.ApartmentManagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackRequest {
    private String title;
    private String content;
    private int userId;
}
