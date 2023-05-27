package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.List;


@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    private final UserServiceImpl userService;

    @Autowired
    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> listUser() {
        return new ResponseEntity<>(userService.listAllUsers(),HttpStatus.OK);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> showUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping(value = "users")
    public ResponseEntity<User> newUser(@RequestBody User user) {
            userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

/*
    @GetMapping
    public String listUser(Model model, Principal principal) {
        model.addAttribute("newUser", new User());
        model.addAttribute("user", userService.findUserByUsername(principal.getName()));
        model.addAttribute("listRoles", roleService.listAllRoles());
        model.addAttribute("listUser", userService.listAllUsers());
        return "admin";
    }

    @GetMapping("/{id}")
    public String showUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUser(id));
        return "redirect:/user";
    }

    @GetMapping("/new")
    public String addNewUser(Model model) {
        List<Role> roles = roleService.listAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("user", new User());
        return "redirect:/";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/editUser")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUser(id));
        List<Role> roles = roleService.listAllRoles();
        model.addAttribute("rolesAdd", roles);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }*/
}
