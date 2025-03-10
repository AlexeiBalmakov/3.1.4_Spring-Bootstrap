package ru.kata.spring.boot_security.demo.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.DataBase.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
}
