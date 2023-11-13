package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.DBUser;
import com.nnk.springboot.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * The UserController class handles HTTP requests related to user management.
 * It includes functionalities such as listing users, adding, updating, and deleting users.
 *
 * @author Marie Moore
 */
@Controller
public class UserController {

    /**
     * The user repository for accessing user data from the database.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Displays the list of users based on the user's role.
     *
     * @param model              the data model for the view
     * @param httpServletRequest the HTTP servlet request
     * @return the view name for the user list
     */
    @RequestMapping("/user/list")
    public String home(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("httpServletRequest", httpServletRequest);
        model.addAttribute("users", userRepository.findAll());
        if (httpServletRequest.isUserInRole("ADMIN")) {
            model.addAttribute("role", "ADMIN");
        }
        else{
            model.addAttribute("role", "USER");
        }
        return "user/list";
    }

    /**
     * Displays the form for adding a new user.
     *
     * @param model the data model for the view
     * @return the view name for adding a user
     */
    @GetMapping("/user/add")
    public String addUser(Model model) {
        model.addAttribute("user", new DBUser());
        return "user/add";
    }

    /**
     * Validates and saves a new user.
     *
     * @param user                the user to be validated and saved
     * @param result              the result of the validation
     * @param redirectAttributes  attributes for the redirect
     * @param model               the data model for the view
     * @return the view name or redirect URL based on the result of the operation
     */
    @PostMapping("/user/validate")
    public String validate(
            @Valid DBUser user,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {
        DBUser alreadyExists = userRepository.findByUsername(user.getUsername());
        if(alreadyExists != null) {
            redirectAttributes.addFlashAttribute("error", "User already exists");
            return "redirect:/user/list";
        }
        if (!result.hasErrors()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            model.addAttribute("users", userRepository.findAll());
            redirectAttributes.addFlashAttribute("success", "User got saved");
            return "redirect:/user/list";
        }
        redirectAttributes.addFlashAttribute("error", "User could not get saved");
        return "redirect:/user/add";
    }

    /**
     * Displays the form for updating an existing user.
     *
     * @param id    the ID of the user to be updated
     * @param model the data model for the view
     * @return the view name for updating a user
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        DBUser user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }

    /**
     * Updates an existing user based on the provided data.
     *
     * @param id                  the ID of the user to be updated
     * @param user                the user data for the update
     * @param result              the result of the validation
     * @param redirectAttributes  attributes for the redirect
     * @param model               the data model for the view
     * @return the view name or redirect URL based on the result of the operation
     */
    @PostMapping("/user/update/{id}")
    public String updateUser(
            @PathVariable("id") Integer id,
            @Valid DBUser user,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {
        if (!result.hasErrors()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            user.setId(id);
            userRepository.save(user);
            model.addAttribute("users", userRepository.findAll());
            redirectAttributes.addFlashAttribute("success", "User got updated");
            return "redirect:/user/list";
        }
        else{
            redirectAttributes.addFlashAttribute("error", "User could not get updated");
            model.addAttribute("user", user);
            return "user/update";
        }
    }

    /**
     * Deletes an existing user based on the provided ID.
     *
     * @param id                  the ID of the user to be deleted
     * @param redirectAttributes  attributes for the redirect
     * @param model               the data model for the view
     * @return the redirect URL after deleting the user
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(
            @PathVariable("id") Integer id,
            RedirectAttributes redirectAttributes,
            Model model) {
        DBUser user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        redirectAttributes.addFlashAttribute("success", "User got deleted");
        return "redirect:/user/list";
    }
}
