package hnqd.project.ApartmentManagement.controller;

import hnqd.project.ApartmentManagement.dto.ResponseObject;
import hnqd.project.ApartmentManagement.dto.RoomRequest;
import hnqd.project.ApartmentManagement.entity.Room;
import hnqd.project.ApartmentManagement.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private IRoomService roomService;

    @PostMapping("/")
    public ResponseEntity<ResponseObject> createRoom(@ModelAttribute RoomRequest roomReq) throws IOException {
        Room roomSave = roomService.createRoom(roomReq);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(
                new ResponseObject("OK", "Create room successfully!", roomSave)
        );
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseObject> getRooms(@RequestParam Map<String, String> params) {
        List<Room> rooms = roomService.getRooms(params);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Get rooms successfully!", rooms)
        );
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject> getRoomsPaging(@RequestParam Map<String, String> params) {
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Get rooms successfully!", roomService.getRoomsPaging(params))
        );
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<ResponseObject> updateRoom(
            @PathVariable int roomId,
            @ModelAttribute RoomRequest roomReq) throws IOException {
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Update rooms successfully!", roomService.updateRoom(roomReq, roomId))
        );
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<ResponseObject> deleteRoom(@PathVariable Integer roomId) throws IOException {
        roomService.deleteRoom(roomId);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Update rooms successfully!", "")
        );
    }
}
