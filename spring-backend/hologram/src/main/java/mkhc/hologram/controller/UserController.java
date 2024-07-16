package mkhc.hologram.controller;

import mkhc.hologram.dto.user.UserFetchDTO;
import mkhc.hologram.dto.user.UserRegisterDTO;
import mkhc.hologram.exception.user.EmailAlreadyUsed;
import mkhc.hologram.exception.user.PhoneNumberAlreadyUsed;
import mkhc.hologram.model.User;
import mkhc.hologram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Long> register(@RequestBody UserRegisterDTO userDTO) {
        long toReturn;
        try{
            toReturn=userService.save(userDTO.toModel()).getUserId();
        } catch (RuntimeException e){
            if(e instanceof EmailAlreadyUsed){
                return ResponseEntity.badRequest().header("Error", "Email already used").build();
            }
            if(e instanceof PhoneNumberAlreadyUsed){
                return ResponseEntity.badRequest().header("Error", "Phone number already used").build();
            }
            else return ResponseEntity.badRequest().header("Error", "Unknown error").build();
        }
        return new ResponseEntity<>(toReturn,HttpStatus.ACCEPTED);
    }

    @GetMapping("/all")
    @ResponseBody
    public List<UserFetchDTO> getAll() {
        return userService
                .findAll()
                .stream()
                .map(User::toFetchDTO)
                .toList();
    }

}
