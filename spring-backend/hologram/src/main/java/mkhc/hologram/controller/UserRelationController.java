package mkhc.hologram.controller;

import mkhc.hologram.exception.userRelation.*;
import mkhc.hologram.model.User;
import mkhc.hologram.service.UserRelationsService;
import mkhc.hologram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/relation")
public class UserRelationController {
    @Autowired
    private UserRelationsService userRelationsService;

    @Autowired
    private UserService userService;

    @PostMapping("/request")
    public void friendRequest(@RequestParam Long senderId, @RequestParam Long receiverId) {
        //POST https://localhost:8000/api/relation/request?senderId=1&receiverId=2
        User sender = userService.findById(senderId).orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Sender not found within app database"));
        User receiver = userService.findById(receiverId).orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Receiver not found within app database"));
        try {
            userRelationsService.saveFriendRequests(sender, receiver);
        } catch (RuntimeException e) {
            switch (e) {
                case FriendshipAlreadyExists _ ->
                        throw new ResponseStatusException(HttpStatus.CONFLICT, "You are already friends!");
                case FriendRequestAlreadySent _ ->
                        throw new ResponseStatusException(HttpStatus.CONFLICT, "Friend request already sent!");
                case UserBlocked _ ->
                        throw new ResponseStatusException(HttpStatus.CONFLICT, "You cannot add as a friend user you have blocked!");
                case UserBlockedYou _ ->
                        throw new ResponseStatusException(HttpStatus.CONFLICT, "User which you are trying to add as a friend blocked you!");
                case FriendRequestsMerged _ ->
                        throw new ResponseStatusException(HttpStatus.CONFLICT, "User you are trying to add as a friend already sent you a friend request, now you are friends!");
                default -> throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!");
            }
        }
    }

    @DeleteMapping("/request")
    public void deleteFriendRequest(@RequestParam Long senderId, @RequestParam Long receiverId) {
        //DELETE https://localhost:8000/api/relation/request?senderId=1&receiverId=2
        User sender = userService.findById(senderId).orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Sender not found within app database"));
        User receiver = userService.findById(receiverId).orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Receiver not found within app database"));
        try {
            userRelationsService.deleteFriendRequests(sender, receiver);
        } catch (RuntimeException e) {
            if (e instanceof FriendRequestNotFound) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Friend request not found!");
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!");
        }
    }

    @PostMapping("/accept")
    public void acceptFriendRequest(@RequestParam Long senderId, @RequestParam Long receiverId) {
        //POST https://localhost:8000/api/relation/accept?senderId=1&receiverId=2
        User sender = userService.findById(senderId).orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Sender not found within app database"));
        User receiver = userService.findById(receiverId).orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Receiver not found within app database"));
        try {
            userRelationsService.saveFriendship(sender, receiver);
        } catch (RuntimeException e) {
            switch (e) {
                case FriendRequestNotFound _ ->
                        throw new ResponseStatusException(HttpStatus.CONFLICT, "Friend request not found!");
                case FriendshipAlreadyExists _ ->
                        throw new ResponseStatusException(HttpStatus.CONFLICT, "You are already friends!");
                default -> throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!");
            }
        }

    }
}