package mkhc.hologram.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ConversationTheme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long conversationThemeId;
    private String conversationThemeName;
}
