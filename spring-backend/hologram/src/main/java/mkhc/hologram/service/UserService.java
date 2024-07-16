package mkhc.hologram.service;

import mkhc.hologram.exception.user.EmailAlreadyUsed;
import mkhc.hologram.exception.user.PhoneNumberAlreadyUsed;
import mkhc.hologram.model.User;
import mkhc.hologram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        if(userRepository.existsByEmail(user.getEmail())) throw new EmailAlreadyUsed();
        if(userRepository.existsByPhoneNumber(user.getPhoneNumber())) throw new PhoneNumberAlreadyUsed();
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
