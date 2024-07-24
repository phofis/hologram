package mkhc.hologram.service;

import jakarta.transaction.Transactional;
import mkhc.hologram.exception.userRelation.*;
import mkhc.hologram.model.User;
import mkhc.hologram.model.userRelation.Block;
import mkhc.hologram.model.userRelation.FriendRequest;
import mkhc.hologram.model.userRelation.Friendship;
import mkhc.hologram.model.userRelation.RelationCompositeKey;
import mkhc.hologram.repository.userRelation.BlockRepository;
import mkhc.hologram.repository.userRelation.FriendRequestsRepository;
import mkhc.hologram.repository.userRelation.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRelationsService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private FriendRequestsRepository friendRequestsRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Transactional
    public void saveFriendRequests(User sender, User receiver) {
        RelationCompositeKey rckey = new RelationCompositeKey().sender(sender).receiver(receiver);
        RelationCompositeKey rckeyReverse = new RelationCompositeKey().receiver(sender).sender(receiver);
        if (friendshipRepository.findById(rckey).isPresent() ||
                friendshipRepository.findById(rckeyReverse).isPresent()) {
            throw new FriendshipAlreadyExists();
        }
        if (friendRequestsRepository.findById(rckey).isPresent()) {
            throw new FriendRequestAlreadySent();
        }
        if (blockRepository.findById(rckey).isPresent()) {
            throw new UserBlocked();
        }
        if (blockRepository.findById(rckeyReverse).isPresent()) {
            throw new UserBlockedYou();
        }
        if (blockRepository.findById(rckey).isPresent()) {
            throw new UserBlocked();
        }
        if (friendRequestsRepository.findById(rckeyReverse).isPresent()) {
            mergeRequestsIntoFriendship(rckey, rckeyReverse);
            throw new FriendRequestsMerged();
        }
        friendRequestsRepository.save(new FriendRequest().setSender(sender).setReceiver(receiver));
    }

    @Transactional
    protected void mergeRequestsIntoFriendship(RelationCompositeKey rckey, RelationCompositeKey rckeyReverse) {
        friendRequestsRepository.deleteById(rckeyReverse);
        friendshipRepository.save(new Friendship().sender(rckey.sender()).receiver(rckey.receiver()));
    }

    @Transactional
    public void saveFriendship(User sender, User receiver) {
        RelationCompositeKey rckey = new RelationCompositeKey().sender(sender).receiver(receiver);
        RelationCompositeKey rckeyReverse = new RelationCompositeKey().receiver(sender).sender(receiver);
        if (friendRequestsRepository.findById(rckey).isEmpty()) {
            throw new FriendRequestNotFound();
        }
        if (friendshipRepository.findById(rckey).isPresent() ||
                friendshipRepository.findById(rckeyReverse).isPresent()) {
            throw new FriendshipAlreadyExists();
        }
        friendRequestsRepository.deleteById(rckey);
        friendshipRepository.save(new Friendship().sender(sender).receiver(receiver));
    }

    public void deleteFriendRequests(User sender, User receiver) {
        RelationCompositeKey rckey = new RelationCompositeKey().sender(sender).receiver(receiver);
        if(friendRequestsRepository.findById(rckey).isEmpty()) {
            throw new FriendRequestNotFound();
        }
        friendRequestsRepository.deleteById(rckey);
    }

    @Transactional
    public void deleteFriendship(User sender, User receiver) {
        RelationCompositeKey rckey = new RelationCompositeKey().sender(sender).receiver(receiver);
        RelationCompositeKey rckeyReverse = new RelationCompositeKey().receiver(sender).sender(receiver);
        if(friendshipRepository.findById(rckey).isEmpty()
                &&friendshipRepository.findById(rckeyReverse).isEmpty()) {
            throw new FriendshipNotFound();
        }
        friendshipRepository.deleteById(rckey);
        friendshipRepository.deleteById(rckeyReverse);
    }

    @Transactional
    public void saveBlock(User sender, User receiver) {
        RelationCompositeKey rckey= new RelationCompositeKey().sender(sender).receiver(receiver);
        RelationCompositeKey rckeyReverse= new RelationCompositeKey().receiver(sender).sender(receiver);
        if(blockRepository.findById(rckey).isPresent()) {
            throw new UserAlreadyBlocked();
        }
        friendRequestsRepository.deleteById(rckey);
        friendRequestsRepository.deleteById(rckeyReverse);
        friendshipRepository.deleteById(rckey);
        friendshipRepository.deleteById(rckeyReverse);
        blockRepository.save(new Block().sender(sender).receiver(receiver));
    }

    public void deleteBlock(User sender, User receiver) {
        RelationCompositeKey rckey= new RelationCompositeKey().sender(sender).receiver(receiver);
        if(blockRepository.findById(rckey).isEmpty()) {
            throw new BlockNotFound();
        }
        blockRepository.deleteById(rckey);
    }


}
