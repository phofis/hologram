package mkhc.hologram.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long conversationId;

    private String customName;

    private String emote;

    @ManyToOne(fetch = FetchType.EAGER)
    private ConversationTheme conversationTheme;

    @OneToOne
    private Picture profilePicture;

}
