package mkhc.hologram.model.userRelation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import mkhc.hologram.model.User;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(fluent = true)
public class RelationCompositeKey {
    private User sender;
    private User receiver;
}
