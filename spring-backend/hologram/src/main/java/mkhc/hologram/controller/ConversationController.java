package mkhc.hologram.controller;

import mkhc.hologram.model.Conversation;
import mkhc.hologram.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/conversation")
public class ConversationController {
    @Autowired
    private ConversationService conversationService;

    @PostMapping
    public Long addConversation() {
        return conversationService.save(new Conversation()).getConversationId();
    }
}
