package mkhc.hologram.model.userRelation;

import jakarta.persistence.*;
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
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(RelationCompositeKey.class)
public class Friendship {
    @Id
    @ManyToOne
    private User sender;

    @Id
    @ManyToOne
    private User receiver;

    @ColumnDefault("current_timestamp")
    private Timestamp timestamp;
}
