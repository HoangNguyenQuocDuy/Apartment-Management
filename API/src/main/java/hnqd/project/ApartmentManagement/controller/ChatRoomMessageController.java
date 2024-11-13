package hnqd.project.ApartmentManagement.controller;

import hnqd.project.ApartmentManagement.dto.ResponseObject;
import hnqd.project.ApartmentManagement.service.IChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/chat/rooms")
public class ChatRoomMessageController {
    @Autowired
    private IChatMessageService chatMessageService;

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<?> getChatMessages(@PathVariable UUID roomId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Get messages for room successfully!",
                            chatMessageService.getChatMessageByRoomId(roomId)
                    ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    new ResponseObject("FAILED", "Failed when get messages for room!", e.getMessage())
            );
        }
    }
}
