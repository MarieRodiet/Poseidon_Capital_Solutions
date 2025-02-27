package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.CurvePointService;
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
public class CurveController {

    @Autowired
    private CurvePointService curvePointService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("httpServletRequest", httpServletRequest);
        model.addAttribute("curvePoints", curvePointService.findAll());
        if (httpServletRequest.isUserInRole("ADMIN")) {
            model.addAttribute("role", "ADMIN");
        }
        else{
            model.addAttribute("role", "USER");
        }
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(Model model) {
        model.addAttribute("curvePoint", new CurvePoint());
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(
            @Valid CurvePoint curvePoint,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {
        if(!result.hasErrors()){
            curvePointService.save(curvePoint);
            model.addAttribute("curvePoints", curvePointService.findAll());
            redirectAttributes.addFlashAttribute("success", "Curve Point got saved");
            return "redirect:/curvePoint/list";
        }
        redirectAttributes.addFlashAttribute("error", "Curve Point could not be saved");
        return "redirect:/curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateCurvePointForm(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.findById(id);
        model.addAttribute("curvePoint", curvePoint);
        curvePoint.setId(id);
        curvePointService.save(curvePoint);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(
            @PathVariable("id") Integer id,
            @Valid CurvePoint curvePoint,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("error", "Curve Point could not get updated");
            return "curvePoint/update";
        }
        curvePoint.setId(id);
        curvePointService.save(curvePoint);
        model.addAttribute("curvePoints", curvePointService.findAll());
        redirectAttributes.addFlashAttribute("success", "Curve Point got updated");
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(
            @PathVariable("id") Integer id,
            RedirectAttributes redirectAttributes,
            Model model) {
        CurvePoint curvePoint = curvePointService.findById(id);
        curvePointService.deleteById(curvePoint.getId());
        model.addAttribute("curvePoints", curvePointService.findAll());
        redirectAttributes.addFlashAttribute("success", "Curve Point got deleted");
        return "redirect:/curvePoint/list";
    }
}
