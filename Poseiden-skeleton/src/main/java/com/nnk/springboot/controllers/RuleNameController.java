package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
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
public class RuleNameController {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    @RequestMapping("/ruleName/list")
    public String home(Model model, HttpServletRequest httpServletRequest){
        model.addAttribute("httpServletRequest", httpServletRequest);
        model.addAttribute("ruleNames", ruleNameRepository.findAll());
        if (httpServletRequest.isUserInRole("ADMIN")) {
            model.addAttribute("role", "ADMIN");
        }
        else{
            model.addAttribute("role", "USER");
        }
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(Model model) {
        model.addAttribute("ruleName", new RuleName());
    return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(
            @Valid RuleName ruleName,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {
        if(!result.hasErrors()){
            ruleNameRepository.save(ruleName);
            model.addAttribute("ruleNames", ruleNameRepository.findAll());
            redirectAttributes.addFlashAttribute("success", "Rule name got saved");
            return "redirect:/ruleName/list";
        }
        redirectAttributes.addFlashAttribute("error", "Rule name could not get saved");
        return "redirect:/ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
        model.addAttribute("ruleName", ruleName);
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id,
                                 @Valid RuleName ruleName,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes,
                                 Model model) {
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("error", "Rule name could not get updated");
            return "ruleName/update";
        }
        ruleName.setId(id);
        ruleNameRepository.save(ruleName);
        model.addAttribute("ruleNames", ruleNameRepository.findAll());
        redirectAttributes.addFlashAttribute("success", "Rule name got updated");
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(
            @PathVariable("id") Integer id,
            RedirectAttributes redirectAttributes,
            Model model) {
        RuleName ruleName = ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
        ruleNameRepository.delete(ruleName);
        model.addAttribute("ruleNames", ruleNameRepository.findAll());
        redirectAttributes.addFlashAttribute("success", "Rule name got deleted");
        return "redirect:/ruleName/list";
    }
}
