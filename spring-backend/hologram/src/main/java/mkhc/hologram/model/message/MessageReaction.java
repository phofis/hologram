package mkhc.hologram.model.message;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mkhc.hologram.model.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(ReactionCompositeKey.class)
public class MessageReaction {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    private Message message;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private User reactionSender;

    private String reaction;
}
