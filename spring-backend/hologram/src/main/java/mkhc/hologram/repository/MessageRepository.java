package mkhc.hologram.repository;

import mkhc.hologram.model.message.Message;
import mkhc.hologram.model.message.MessageCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, MessageCompositeKey> {
}
