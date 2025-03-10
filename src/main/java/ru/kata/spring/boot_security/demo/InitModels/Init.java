package ru.kata.spring.boot_security.demo.InitModels;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.DataBase.Role;
import ru.kata.spring.boot_security.demo.DataBase.User;
import ru.kata.spring.boot_security.demo.UserRepository.RoleRepo;
import ru.kata.spring.boot_security.demo.UserService.UserService;

import javax.annotation.PostConstruct;

@Component
public class Init {

    private final UserService userService;
    private final RoleRepo roleRepository;

    public Init(UserService userService, RoleRepo roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        roleRepository.save(new Role("ROLE_ADMIN"));
        roleRepository.save(new Role("ROLE_USER"));
        userService.save(new User("Админ", "Админов", "admin", "admin", 25, roleRepository.findAll()));
        userService.save(new User("Юзер", "Юзеров", "user", "user", 33, roleRepository.findById(2).stream().toList()));

    }
}
