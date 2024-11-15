package hnqd.project.ApartmentManagement.repository;

import hnqd.project.ApartmentManagement.model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface IChatMessageRepo extends MongoRepository<ChatMessage, UUID> {
    List<ChatMessage> findChatMessageByChatRoomId(UUID chatRoomId);
}
