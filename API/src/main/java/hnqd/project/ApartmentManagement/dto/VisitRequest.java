package hnqd.project.ApartmentManagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class VisitRequest {
    private Timestamp entryTime;
    private Timestamp exitTime;
    private int roomId;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String visitReason;
}
