package hnqd.project.ApartmentManagement.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class NotifyController {

    @MessageMapping("/ws")
    @SendTo("/topic/notify")
    public String send(String notify) {
        return notify;
    }
}
