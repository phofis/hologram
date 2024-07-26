package mkhc.hologram.service;

import mkhc.hologram.model.Conversation;
import mkhc.hologram.repository.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    public Conversation save(Conversation conversation) {
        return conversationRepository.save(conversation);
    }
}
