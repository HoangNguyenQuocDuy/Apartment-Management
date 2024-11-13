package hnqd.project.ApartmentManagement.service;

import hnqd.project.ApartmentManagement.dto.ChatRoomRequestDto;
import hnqd.project.ApartmentManagement.model.ChatRoom;

import java.util.List;
import java.util.UUID;

public interface IChatRoomService {
    ChatRoom createRoom(ChatRoomRequestDto chatRoomBody);
    ChatRoom getRoomById(UUID roomId);
    List <ChatRoom> findRoomsByUserId(int userId);
    void deleteRoom(UUID roomId);
    ChatRoom addUserToRoom(UUID roomId, List<String> userId);
    ChatRoom updateRoom(ChatRoom chatRoom);
}
