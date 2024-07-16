package mkhc.hologram.controller;

import mkhc.hologram.dto.user.UserFetchDTO;
import mkhc.hologram.dto.user.UserRegisterDTO;
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
        return new ResponseEntity<>(userService.save(userDTO.toModel()).getUserId(),HttpStatus.ACCEPTED);
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

//    @PostMapping("/photo")
//    @ResponseBody
//    public Optional<Long> updateProfilePicture(@RequestBody MultipartFile file) {
//
//    }
}
