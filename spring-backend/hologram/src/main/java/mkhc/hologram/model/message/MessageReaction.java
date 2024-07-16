package mkhc.hologram.model.message;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mkhc.hologram.model.Conversation;
import mkhc.hologram.model.User;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(ReactionCompositeKey.class)
public class MessageReaction {
    @Id
    @ManyToOne
    private User messageSender;

    @Id
    @ManyToOne
    private Conversation conversation;

    @Id
    private Timestamp messageTimestamp;
    //data of message the reaction is referring to

    @Id
    @ManyToOne
    private User reactionSender;
}
