package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.RatingService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @RequestMapping("/rating/list")
    public String home(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("httpServletRequest", httpServletRequest);
        model.addAttribute("ratings", ratingService.findAll());
        if (httpServletRequest.isUserInRole("ADMIN")) {
            model.addAttribute("role", "ADMIN");
        }
        else{
            model.addAttribute("role", "USER");
        }
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(
            @Valid Rating rating,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {
        if(!result.hasErrors()){
            ratingService.save(rating);
            redirectAttributes.addFlashAttribute("success", "Rating got saved");
            model.addAttribute("ratings", ratingService.findAll());
            return "redirect:/rating/list";
        }
        redirectAttributes.addFlashAttribute("error", "Rating could not get saved");
        return "redirect:/rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.findById(id);
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(
            @PathVariable("id") Integer id,
            @Valid Rating rating,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {
       if(result.hasErrors()){
           redirectAttributes.addFlashAttribute("error", "Rating could not get updated");
           return "rating/update";
       }
       rating.setId(id);
        ratingService.save(rating);
       redirectAttributes.addFlashAttribute("success", "Rating got updated");
       model.addAttribute("rating", rating);
       return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(
            @PathVariable("id") Integer id,
            RedirectAttributes redirectAttributes,
            Model model) {
        Rating rating = ratingService.findById(id);
        ratingService.deleteById(rating.getId());
        model.addAttribute("ratings", ratingService.findAll());
        redirectAttributes.addFlashAttribute("success", "Rating got deleted");
        return "redirect:/rating/list";
    }
}
