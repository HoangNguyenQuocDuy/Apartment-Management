package hnqd.project.ApartmentManagement.service.Impl;

import hnqd.project.ApartmentManagement.dto.ParkingRightRequest;
import hnqd.project.ApartmentManagement.entity.ParkingRight;
import hnqd.project.ApartmentManagement.entity.Relative;
import hnqd.project.ApartmentManagement.exceptions.CommonException;
import hnqd.project.ApartmentManagement.repository.IParkingRightRepo;
import hnqd.project.ApartmentManagement.repository.IRelativeRepo;
import hnqd.project.ApartmentManagement.service.IParkingRightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ParkingRightService implements IParkingRightService {

    @Autowired
    private IParkingRightRepo parkingRightRepo;
    @Autowired
    private IRelativeRepo relativeRepo;

    @Override
    public ParkingRight createParkingRight(ParkingRightRequest prReq) throws CommonException.NotFoundException {
        Relative storedRelative = relativeRepo.findById(prReq.getRelativeId()).orElseThrow(
                () -> new CommonException.NotFoundException("Relative not found with ID: " + prReq.getRelativeId())
        );

        ParkingRight parkingRight = new ParkingRight();

        parkingRight.setLicensePlates(prReq.getLicensePlates());
        parkingRight.setTypeOfVehicle(prReq.getTypeOfVehicle());
        parkingRight.setRelative(storedRelative);
        parkingRight.setCreatedAt(new Timestamp(new Date().getTime()));
        parkingRight.setStatus("Pending");

        return parkingRightRepo.save(parkingRight);
    }

    @Override
    public List<ParkingRight> getParkingRights(Map<String, String> params) {
        List<ParkingRight> parkingRights = new ArrayList<>();

        String status = params.getOrDefault("status", "");
        int userId = Integer.parseInt(params.getOrDefault("userId", "0"));

        if (!status.isEmpty() && userId != 0) {
            parkingRights.addAll(parkingRightRepo.findByStatusAndRelativeUserId(status, userId));
        } else if (status.isEmpty() && userId != 0) {
            parkingRights.addAll(parkingRightRepo.findAllByRelativeUserId(userId));
        } else if (!status.isEmpty()) {
            parkingRights.addAll(parkingRightRepo.findByStatus(status));
        } else {
            parkingRights.addAll(parkingRightRepo.findAll());
        }

        return parkingRights;
    }

    @Override
    public ParkingRight updateParkingRight(int prId, Map<String, String> params) {
        ParkingRight storedParkingRight = parkingRightRepo.findById(prId).orElseThrow(
                () -> new CommonException.NotFoundException("Parking right not found with ID: " + prId)
        );

        if (params.get("status") != null && !params.get("status").isEmpty()) {
            storedParkingRight.setStatus(params.get("status"));
        }
        if (params.get("typeOfVehicle") != null && !params.get("typeOfVehicle").isEmpty()) {
            storedParkingRight.setStatus(params.get("typeOfVehicle"));
        }
        if (params.get("licensePlates") != null && !params.get("licensePlates").isEmpty()) {
            storedParkingRight.setStatus(params.get("licensePlates"));
        }

        storedParkingRight.setUpdatedAt(new Timestamp(new Date().getTime()));

        return parkingRightRepo.save(storedParkingRight);
    }
}
