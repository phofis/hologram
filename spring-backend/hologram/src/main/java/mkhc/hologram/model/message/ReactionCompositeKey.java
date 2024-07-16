package mkhc.hologram.model.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mkhc.hologram.model.Conversation;
import mkhc.hologram.model.User;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReactionCompositeKey {
    private User messageSender;
    private Conversation conversation;
    private Timestamp messageTimestamp;
    //data of message the reaction is referring to

    private User reactionSender;
}
