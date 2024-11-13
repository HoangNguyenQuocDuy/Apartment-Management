package hnqd.project.ApartmentManagement.repository;

import hnqd.project.ApartmentManagement.model.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface IChatRoomRepo extends MongoRepository<ChatRoom, UUID> {
    List<ChatRoom> findByUserIdsContaining(Integer userId);
}
