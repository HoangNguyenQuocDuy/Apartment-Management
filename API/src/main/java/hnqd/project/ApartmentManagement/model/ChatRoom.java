package hnqd.project.ApartmentManagement.model;

import hnqd.project.ApartmentManagement.dto.UserResponse;
import hnqd.project.ApartmentManagement.entity.User;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "rooms")
public class ChatRoom implements Comparable<ChatRoom> {
    @Id
    private UUID id;
    private Set<Integer> userIds;
    private String lastMessage;
    private String roomName;
    private Date updatedAt;
    private Date createdAt;

//    public void addUser(UserResponse user) {
//        users.add(user);
//    }

    @Override
    public int compareTo(ChatRoom otherChatRoom) {
        Date updatedDate1 = this.getUpdatedAt();
        Date updatedDate2 = otherChatRoom.getUpdatedAt();

        if (updatedDate1 != null && updatedDate2!=null) {
            return updatedDate2.compareTo(updatedDate1);
        } else if (updatedDate1 == null && updatedDate2==null) {
            return 1;
        } else if (updatedDate1 != null) {
            return -1;
        } else {
            return otherChatRoom.getCreatedAt().compareTo(this.getCreatedAt());
        }
    }
}
