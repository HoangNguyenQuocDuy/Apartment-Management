package hnqd.project.ApartmentManagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisitorRequest {
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String visitReason;
}
