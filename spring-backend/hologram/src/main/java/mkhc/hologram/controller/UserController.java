package mkhc.hologram.controller;

import mkhc.hologram.dto.user.UserRegisterDTO;
import mkhc.hologram.exception.user.EmailAlreadyUsed;
import mkhc.hologram.exception.user.PhoneNumberAlreadyUsed;
import mkhc.hologram.model.User;
import mkhc.hologram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @GetMapping
    @ResponseBody
    public User getUser(@RequestParam Long userId) {
        Optional<User> user = userService.findById(userId);
        if(user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User not found");
        }
        return user.get();
    }

    @GetMapping("/login")
    @ResponseBody
    public User login(@RequestParam String email, @RequestParam String password) {
        if(userService.findByEmail(email).isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User not found");
        }
        if(userService.findByEmail(email).get().getPassword().equals(passwordEncoder.encode(password))){
            return userService.findByEmail(email).get();
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Provided password is incorrect");
    }

    @PutMapping
    @ResponseBody
    public User updateUser(@RequestBody User user) {
        if(userService.findById(user.getUserId()).isEmpty()){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User not found within the app database");
        }

        if(userService.findById(user.getUserId()).get().getEmail().equals(user.getEmail())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "New email must differentiate from older one");
        }
        if(userService.findById(user.getUserId()).get().getPhoneNumber().equals(user.getPhoneNumber())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "New phone number must differentiate from older one");
        }

        try {
            return userService.update(user);
        } catch (RuntimeException e){
            if(e instanceof EmailAlreadyUsed){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already taken");
            }
            if(e instanceof PhoneNumberAlreadyUsed){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already taken");
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong");
        }
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
