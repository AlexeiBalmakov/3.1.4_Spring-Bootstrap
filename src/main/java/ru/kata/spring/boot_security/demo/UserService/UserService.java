package ru.kata.spring.boot_security.demo.UserService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.DataBase.User;
import ru.kata.spring.boot_security.demo.UserRepository.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {


    private final UserRepo userRepo;


    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User findById(int id) {
        Optional<User> findUser = userRepo.findById(id);
        return findUser.orElse(null);
    }


    @Transactional
    public void save(User user) {
        userRepo.save(user);
    }

    @Transactional
    public void delete(int id) {
        userRepo.deleteById(id);
    }

    @Transactional
    public void update(int id, User user) {
        user.setId(id);
        userRepo.save(user);
    }
}
