package hnqd.project.ApartmentManagement.controller;

import hnqd.project.ApartmentManagement.dto.ResponseObject;
import hnqd.project.ApartmentManagement.entity.Locker;
import hnqd.project.ApartmentManagement.service.ILockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/lockers")
public class LockerController {

    @Autowired
    private ILockerService lockerService;

    @PostMapping("/")
    public ResponseEntity<ResponseObject> createLocker() {
        return ResponseEntity.ok().body(
                new ResponseObject("201", "Create locker successful!", lockerService.createLocker())
        );
    }

    @DeleteMapping("/")
    public ResponseEntity<ResponseObject> deleteLocker(@RequestParam int id) {
        lockerService.deleteLocker(id);
        return ResponseEntity.ok().body(
                new ResponseObject("200", "Delete locker successful!", "")
        );
    }

    @PutMapping("/{lockerId}")
    public ResponseEntity<ResponseObject> updateLocker(@PathVariable int lockerId, @RequestBody Map<String, String> body) {
        return ResponseEntity.ok().body(
                new ResponseObject("200", "Update locker successful!", lockerService.updateLocker(lockerId, body))
        );
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject> getLockers(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok().body(
                new ResponseObject("200", "Get lockers successful!", lockerService.getLockers(params))
        );
    }
}
