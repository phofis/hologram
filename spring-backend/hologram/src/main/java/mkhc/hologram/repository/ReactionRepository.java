package mkhc.hologram.repository;

import mkhc.hologram.model.message.MessageReaction;
import mkhc.hologram.model.message.ReactionCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepository extends JpaRepository<MessageReaction, ReactionCompositeKey> {
}
