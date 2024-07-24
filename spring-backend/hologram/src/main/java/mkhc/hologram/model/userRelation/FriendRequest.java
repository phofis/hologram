package mkhc.hologram.model.userRelation;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import mkhc.hologram.model.User;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Timestamp;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(RelationCompositeKey.class)
public class FriendRequest {
    @Id
    @ManyToOne
    private User sender;

    @Id
    @ManyToOne
    private User receiver;

    @ColumnDefault("current_timestamp")
    private Timestamp timestamp;

    @ColumnDefault("PENDING")
    private Status status;
}
