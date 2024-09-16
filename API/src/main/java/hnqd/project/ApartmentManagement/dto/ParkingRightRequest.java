package hnqd.project.ApartmentManagement.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ParkingRightRequest {
    private String typeOfVehicle;
    private String licensePlates;
    private Integer relativeId;
}
