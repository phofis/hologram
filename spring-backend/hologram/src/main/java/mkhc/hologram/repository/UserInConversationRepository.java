package mkhc.hologram.repository;

import mkhc.hologram.model.UserInConversation;
import mkhc.hologram.model.UserInConversationCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInConversationRepository extends JpaRepository<UserInConversation, UserInConversationCompositeKey> {
}
