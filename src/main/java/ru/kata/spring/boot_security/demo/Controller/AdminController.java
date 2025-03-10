package ru.kata.spring.boot_security.demo.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.DataBase.User;
import ru.kata.spring.boot_security.demo.UserRepository.RoleRepo;
import ru.kata.spring.boot_security.demo.UserRepository.UserRepo;
import ru.kata.spring.boot_security.demo.UserService.UserService;

import java.security.Principal;

@Controller
public class AdminController {

    private final UserService userService;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    public AdminController(UserService userService, UserRepo userRepo, RoleRepo roleRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @GetMapping("/admin")
    public String allUsers(Model model, Principal principal) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("userAuth", userRepo.findByUsername(principal.getName()));
        model.addAttribute("roles", roleRepo.findAll());
        return "index";
    }

    @GetMapping("/admin/addNewUser")
    public String addUser(Model model) {
        model.addAttribute("users", new User());
        return "addNewUser";
    }

    @PostMapping("/admin/addNewUser")
    public String addNewUser(@ModelAttribute("users") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin")
    public String deleteUser(@RequestParam("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/editUser")
    public String editUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("users", userService.findById(id));
        return "editUser";
    }

    @PostMapping("/admin/editUser")
    public String editSaveUser(@ModelAttribute User user) {
        userService.update(user.getId(), user);
        return "redirect:/admin";
    }

}
