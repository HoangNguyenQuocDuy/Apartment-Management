package hnqd.project.ApartmentManagement.service;


import hnqd.project.ApartmentManagement.dto.ChatMessageRequestDto;
import hnqd.project.ApartmentManagement.model.ChatMessage;

import java.util.List;
import java.util.UUID;

public interface IChatMessageService {
    ChatMessage createChatMessage(ChatMessageRequestDto chatMessage);
    List<ChatMessage> getChatMessageByRoomId(UUID roomId);
}
