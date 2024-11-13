package hnqd.project.ApartmentManagement.service;

import hnqd.project.ApartmentManagement.dto.ParkingRightRequest;
import hnqd.project.ApartmentManagement.entity.ParkingRight;
import hnqd.project.ApartmentManagement.exceptions.CommonException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IParkingRightService {
    ParkingRight createParkingRight(ParkingRightRequest pr);

    List<ParkingRight> getParkingRights(Map<String, String> params);

    ParkingRight updateParkingRight(int prId, Map<String, String> params) throws CommonException.NotFoundException;

    Page<ParkingRight> getParkingRightsPaging(Map<String, String> params);
}
