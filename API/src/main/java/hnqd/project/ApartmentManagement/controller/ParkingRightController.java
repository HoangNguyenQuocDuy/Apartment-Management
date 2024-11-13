package hnqd.project.ApartmentManagement.controller;

import hnqd.project.ApartmentManagement.dto.ParkingRightRequest;
import hnqd.project.ApartmentManagement.dto.ResponseObject;
import hnqd.project.ApartmentManagement.service.IParkingRightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/parkingRights")
public class ParkingRightController {

    @Autowired
    private IParkingRightService parkingRightService;

    @GetMapping("/list")
    public ResponseEntity<ResponseObject> getParkingRights(@RequestParam Map<String, String> params) {
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Get parking rights successfully!",
                        parkingRightService.getParkingRights(params))
        );
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject> getParkingRightsPaging(@RequestParam Map<String, String> params) {
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Get parking rights successfully!",
                        parkingRightService.getParkingRightsPaging(params))
        );
    }

    @PostMapping("/")
    public ResponseEntity<?> createParkingRight(@RequestBody ParkingRightRequest pReq) {
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(
                new ResponseObject("OK", "Create parking rights successfully!",
                        parkingRightService.createParkingRight(pReq))
        );
    }

    @PutMapping("/{pId}")
    public ResponseEntity<?> updateParkingRight(@RequestBody Map<String, String> params,
                                                @PathVariable("pId") int pId) {
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(
                new ResponseObject("OK", "Update parking rights successfully!",
                        parkingRightService.updateParkingRight(pId, params)
                )
        );
    }
}
