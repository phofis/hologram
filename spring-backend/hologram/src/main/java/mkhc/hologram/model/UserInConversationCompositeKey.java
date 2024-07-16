package mkhc.hologram.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInConversationCompositeKey{
    private Conversation conversation;
    private User user;
}
