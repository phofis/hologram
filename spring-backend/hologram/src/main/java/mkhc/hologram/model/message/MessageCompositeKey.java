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
public class MessageCompositeKey {
    private User user;
    private Conversation conversation;
    private Timestamp timestamp;
}
