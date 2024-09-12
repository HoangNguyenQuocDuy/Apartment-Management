package hnqd.project.ApartmentManagement.controller;

import hnqd.project.ApartmentManagement.dto.ResponseObject;
import hnqd.project.ApartmentManagement.service.ILockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lockers")
public class LockerController {

    @Autowired
    private ILockerService lockerService;

    @PostMapping("/")
    public ResponseEntity<ResponseObject> createLocker() {
        return ResponseEntity.ok().body(
                new ResponseObject("201", "Create locker successful!", "")
        );
    }

    @DeleteMapping("/")
    public ResponseEntity<ResponseObject> deleteLocker(@RequestParam int id) {
        lockerService.deleteLocker(id);
        return ResponseEntity.ok().body(
                new ResponseObject("200", "Delete locker successful!", "")
        );
    }

}
