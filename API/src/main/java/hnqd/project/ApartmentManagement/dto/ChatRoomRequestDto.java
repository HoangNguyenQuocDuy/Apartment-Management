package hnqd.project.ApartmentManagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ChatRoomRequestDto {
    private Set<Integer> usersIds;
}
