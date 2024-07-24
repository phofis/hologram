package mkhc.hologram.repository.userRelation;

import mkhc.hologram.model.userRelation.FriendRequest;
import mkhc.hologram.model.userRelation.RelationCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestsRepository extends JpaRepository<FriendRequest, RelationCompositeKey> {
}
