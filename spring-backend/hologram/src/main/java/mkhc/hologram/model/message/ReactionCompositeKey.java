package mkhc.hologram.model.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mkhc.hologram.model.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReactionCompositeKey {
    private Message message;
    private User reactionSender;
}
