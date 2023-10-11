package com.oleza.spring.springboot.task_312.controller;

import com.oleza.spring.springboot.task_312.model.User;
import com.oleza.spring.springboot.task_312.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/users")
public class UsersController {

    private final UsersService userService;
    @Autowired
    public UsersController(UsersService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/showUsers";
    }

    @GetMapping("/showuser")
    public String showUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.findOne(id));
        return "users/showUser";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/newUser";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "users/new";

        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String editUser(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userService.findOne(id));
        return "users/editUser";
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @RequestParam("id") int id) {
        if (bindingResult.hasErrors())
            return "users/editUser";

        userService.update(id, user);
        return "redirect:/users";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteUser(@RequestParam("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
