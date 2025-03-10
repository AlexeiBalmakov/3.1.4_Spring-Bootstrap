package ru.kata.spring.boot_security.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.UserRepository.UserRepo;

import java.security.Principal;

@Controller
public class UserController {

    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/user")
    public String allUsers(Model model, Principal principal) {
        model.addAttribute("users", userRepo.findByUsername(principal.getName()));
        model.addAttribute("userAuth", userRepo.findByUsername(principal.getName()));
        return "index";
    }

    @GetMapping(value = "/")
    public String getLoginPage() {
        return "redirect:/login";
    }

}
