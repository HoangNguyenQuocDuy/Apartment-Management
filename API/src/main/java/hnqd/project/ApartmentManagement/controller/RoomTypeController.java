package hnqd.project.ApartmentManagement.controller;

import hnqd.project.ApartmentManagement.dto.ResponseObject;
import hnqd.project.ApartmentManagement.service.IRoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roomTypes")
public class RoomTypeController {
    @Autowired
    private IRoomTypeService roomTypeService;

    @GetMapping("/")
    public ResponseEntity<ResponseObject> getListRoomType() {
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Get list room type successfully!", roomTypeService.getRoomTypes())
        );
    }
}
