package hnqd.project.ApartmentManagement.controller;

import hnqd.project.ApartmentManagement.dto.ChatMessageRequestDto;
import hnqd.project.ApartmentManagement.dto.ChatRoomRequestDto;
import hnqd.project.ApartmentManagement.dto.ResponseObject;
import hnqd.project.ApartmentManagement.entity.User;
import hnqd.project.ApartmentManagement.model.ChatMessage;
import hnqd.project.ApartmentManagement.model.ChatRoom;
import hnqd.project.ApartmentManagement.service.IChatMessageService;
import hnqd.project.ApartmentManagement.service.IChatRoomService;
import hnqd.project.ApartmentManagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/chat/rooms")
public class ChatRoomController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private IChatRoomService chatRoomService;
    @Autowired
    private IChatMessageService chatMessageService;
    @Autowired
    private IUserService userService;

    @MessageMapping("/sendMessage")
    public ChatMessage sendMessage(@Payload ChatMessageRequestDto chatMessageReq) {
        ChatMessage chatMessageSaved = chatMessageService.createChatMessage(chatMessageReq);
        ChatRoom chatRoom = chatRoomService.getRoomById(chatMessageSaved.getChatRoomId());
        chatRoom.setUpdatedAt(new Date());
        chatRoom.setLastMessage(chatMessageSaved.getContent());
        chatRoomService.updateRoom(chatRoom);

        messagingTemplate.convertAndSend(String.format("/notification/rooms/%s", chatMessageSaved.getChatRoomId()), chatMessageSaved);

        return chatMessageSaved;
    }

    @MessageMapping("/createRoom")
    public ChatRoom addRoom(@Payload ChatRoomRequestDto chatRoomRequestDto) {
        try {
            ChatRoom chatRoom = chatRoomService.createRoom(chatRoomRequestDto);
            messagingTemplate.convertAndSend("/rooms", chatRoom);

            return chatRoom;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @MessageMapping("/chat.addUserToChatRoom")
//    public ChatRoom addUserToChatRoom(@Payload Map<String, Object> payload) {
//        try {
//            UUID roomId = UUID.fromString((String) payload.get("roomId"));
//            List<String> userIds = (List<String>) payload.get("usersIds");
//
//            ChatRoom chatRoom = chatRoomService.addUserToRoom(roomId, userIds);
//            messagingTemplate.convertAndSend(String.format("/rooms/%s/addUser", roomId), chatRoom);
//
//            return chatRoom;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @MessageMapping("/chat.deleteUserFromRoom")
//    public ChatRoom deleteUserFromRoom(@Payload Map<String, Object> payload) {
//        try {
//            UUID roomId = UUID.fromString((String) payload.get("roomId"));
//            UUID userId = UUID.fromString((String) payload.get("userId"));
//
//            ChatRoom chatRoom = chatRoomService.deleteUserFromRoom(roomId, userId);
//            messagingTemplate.convertAndSend(String.format("/rooms/%s/deleteUser", roomId), chatRoom);
//
//            return chatRoom;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }


//    @GetMapping("/{roomId}")
//    public ResponseEntity<ResponseObject> getRoom(@PathVariable UUID roomId) {
//        try {
//            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
//                    new ResponseObject("OK", "Get room by id successfully", chatRoomService.getRoomById(roomId))
//            );
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
//                    new ResponseObject("FAILED", "Failed when get room by id!", e.getMessage())
//            );
//        }
//    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject> getRooms(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            User user = userService.getUserByUsername(userDetails.getUsername());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Get rooms successfully", chatRoomService.findRoomsByUserId(user.getId()))
            );
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    new ResponseObject("FAILED", "Failed when get room by userId!", e.getMessage())
            );
        }
    }
}
