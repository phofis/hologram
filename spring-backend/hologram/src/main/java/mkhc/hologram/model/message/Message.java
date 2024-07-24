package mkhc.hologram.model.message;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mkhc.hologram.model.Conversation;
import mkhc.hologram.model.User;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@IdClass(MessageCompositeKey.class)
public class Message {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Conversation conversation;

    @Id
    @Column(unique = true, nullable = false)
    @ColumnDefault("current_timestamp")
    private Timestamp timestamp;

    @Column(columnDefinition = "TEXT")
    private String text;

    @OneToOne(fetch = FetchType.EAGER)
    private MyFile myFile;

    @ColumnDefault("false")
    private Boolean isForwarded;

    @ManyToOne(fetch = FetchType.EAGER)
    private Message replyTo;
    // message that THIS message is replying TO (null if THIS message is not a reply to any other message)

    @ColumnDefault("false")
    private Boolean isDeleted;
}
