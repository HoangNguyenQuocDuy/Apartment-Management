package hnqd.project.ApartmentManagement.controller;

import hnqd.project.ApartmentManagement.dto.ResponseObject;
import hnqd.project.ApartmentManagement.entity.Room;
import hnqd.project.ApartmentManagement.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private IRoomService roomService;

    @PostMapping("/")
    public ResponseEntity<ResponseObject> createRoom(@ModelAttribute Room room) {
        try {
            Room roomSave = roomService.createRoom(room);
            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(
                    new ResponseObject("OK", "Create room successfully!", roomSave)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(
                    new ResponseObject("FAILED", "Failed when trying create room!", e.getMessage())
            );
        }
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject> getRooms() {
        try {
            List<Room> rooms = roomService.getRooms();
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                    new ResponseObject("OK", "Get rooms successfully!", rooms)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(
                    new ResponseObject("FAILED", "Failed when getting rooms !", e.getMessage())
            );
        }
    }
}
