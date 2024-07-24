package mkhc.hologram.controller;

import mkhc.hologram.dto.user.UserRegisterDTO;
import mkhc.hologram.exception.user.EmailAlreadyUsed;
import mkhc.hologram.exception.user.PhoneNumberAlreadyUsed;
import mkhc.hologram.model.User;
import mkhc.hologram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseBody
    public Long register(@RequestBody UserRegisterDTO userDTO) {
        long toReturn;
        try{
            toReturn=userService.save(userDTO.toModel()).getUserId();
        } catch (RuntimeException e){
            if(e instanceof EmailAlreadyUsed){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already taken");
            }
            if(e instanceof PhoneNumberAlreadyUsed){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already taken");
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong");
        }
        return toReturn;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Optional<User> getUser(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if(user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User not found");
        }
        return user;
    }

    @PutMapping
    @ResponseBody
    public User updateUser(@RequestBody User user) {
        User newUser = userService.findByEmail(user.getEmail());
        if(newUser != null && !Objects.equals(newUser.getUserId(), user.getUserId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already taken");
        }
        newUser = userService.findByPhoneNumber(user.getPhoneNumber());
        if(newUser != null && !Objects.equals(newUser.getUserId(), user.getUserId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already taken");
        }
        return user;
    }

    @GetMapping("/all")
    @ResponseBody
    public List<User> getAll() {
        return userService
                .findAll()
//                .stream()
//                .map(User::toFetchDTO)
//                .toList();
                ;
    }

}
