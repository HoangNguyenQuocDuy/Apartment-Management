package hnqd.project.ApartmentManagement.service.Impl;

import hnqd.project.ApartmentManagement.dto.ChatMessageRequestDto;
import hnqd.project.ApartmentManagement.model.ChatMessage;
import hnqd.project.ApartmentManagement.repository.IChatMessageRepo;
import hnqd.project.ApartmentManagement.service.IChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ChatRoomMessageServiceImpl implements IChatMessageService {
    @Autowired
    private IChatMessageRepo chatMessageRepo;

    @Override
    public ChatMessage createChatMessage(ChatMessageRequestDto chatMessageReq) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setId(UUID.randomUUID());
        chatMessage.setUserId(chatMessageReq.getUserId());
        chatMessage.setContent(chatMessageReq.getContent());
        chatMessage.setChatRoomId(chatMessageReq.getChatRoomId());
        chatMessage.setCreatedAt(new Date());

        return chatMessageRepo.save(chatMessage);
    }

    @Override
    public List<ChatMessage> getChatMessageByRoomId(UUID roomId) {
        return chatMessageRepo.findChatMessageByChatRoomId(roomId);
    }
}
