package hnqd.project.ApartmentManagement.service.Impl;

import hnqd.project.ApartmentManagement.dto.ChatRoomRequestDto;
import hnqd.project.ApartmentManagement.entity.User;
import hnqd.project.ApartmentManagement.exceptions.CommonException;
import hnqd.project.ApartmentManagement.model.ChatRoom;
import hnqd.project.ApartmentManagement.repository.IChatRoomRepo;
import hnqd.project.ApartmentManagement.repository.IUserRepo;
import hnqd.project.ApartmentManagement.service.IChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatRoomServiceImp implements IChatRoomService {
    @Autowired
    private IUserRepo userRepo;
    @Autowired
    private IChatRoomRepo chatRoomRepo;

    @Override
    public ChatRoom createRoom(ChatRoomRequestDto chatRoomBody) {
        Set<Integer> userIds = new HashSet<>();

        chatRoomBody.getUsersIds().forEach(userId -> {
            User user = userRepo.findById(userId).orElseThrow(
                    () -> new CommonException.NotFoundException("User not found!")
            );
            userIds.add(user.getId());
        });

        ChatRoom newChatRoom = ChatRoom.builder()
                .id(UUID.randomUUID())
                .userIds(userIds)
                .createdAt(new Date())
                .build();

        return chatRoomRepo.save(newChatRoom);
    }

    @Override
    public ChatRoom getRoomById(UUID roomId) {
        ChatRoom chatRoom = chatRoomRepo.findById(roomId).orElseThrow(
                () -> new CommonException.NotFoundException("Room with id " + roomId + " not found!")
        );
        ;
        return chatRoom;
    }

    @Override
    public List<ChatRoom> findRoomsByUserId(int userId) {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new CommonException.NotFoundException("User not found!")
        );
        return chatRoomRepo.findByUserIdsContaining(user.getId());
    }

    @Override
    public void deleteRoom(UUID roomId) {

    }

    @Override
    public ChatRoom addUserToRoom(UUID roomId, List<String> userId) {
        return null;
    }

    @Override
    public ChatRoom updateRoom(ChatRoom chatRoom) {
        return null;
    }
}
