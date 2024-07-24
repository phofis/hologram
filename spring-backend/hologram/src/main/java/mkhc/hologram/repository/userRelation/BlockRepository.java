package mkhc.hologram.repository.userRelation;

import mkhc.hologram.model.userRelation.Block;
import mkhc.hologram.model.userRelation.RelationCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block, RelationCompositeKey> {
}
