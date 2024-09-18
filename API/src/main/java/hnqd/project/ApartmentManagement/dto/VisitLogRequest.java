package hnqd.project.ApartmentManagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class VisitLogRequest {
    private Timestamp entryTime;
    private Timestamp exitTime;
    private int visitorId;
    private int roomId;
}
