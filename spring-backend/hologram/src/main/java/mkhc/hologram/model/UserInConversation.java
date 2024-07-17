package mkhc.hologram.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mkhc.hologram.model.message.Message;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(UserInConversationCompositeKey.class)
public class UserInConversation {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Conversation conversation;

    @Column(nullable = false)
    private Boolean isAdmin;

    @ManyToOne(fetch = FetchType.LAZY)
    private Message lastSeenMessage;
}
