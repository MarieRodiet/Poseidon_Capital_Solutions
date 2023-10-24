package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
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
public class TradeController {

    @Autowired
    private TradeRepository tradeRepository;

    @RequestMapping("/trade/list")
    public String home(Model model) {
        model.addAttribute("trades", tradeRepository.findAll());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addTrade(Model model) {
        model.addAttribute("trade", new Trade());
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(
            @Valid Trade trade,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {
        if(!result.hasErrors()){
            tradeRepository.save(trade);
            redirectAttributes.addFlashAttribute("success", "Trade got saved");
            model.addAttribute("trades", tradeRepository.findAll());
            return "redirect:/trade/list";
        }
        redirectAttributes.addFlashAttribute("error", "Trade could not get saved");
        return "redirect:/trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeRepository.findById(id).orElseThrow(() -> new IllegalStateException("Invalid trade id " + id));
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(
            @PathVariable("id") Integer id,
            @Valid Trade trade,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("error", "Trade could not get updated");
            return "trade/update";
        }
        trade.setTradeId(id);
        tradeRepository.save(trade);
        model.addAttribute("trade", trade);
        redirectAttributes.addFlashAttribute("success", "Trade got updated");
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(
            @PathVariable("id") Integer id,
            RedirectAttributes redirectAttributes,
            Model model) {
        Trade trade = tradeRepository.findById(id).orElseThrow(() -> new IllegalStateException("Invalid trade id " + id));
        tradeRepository.delete(trade);
        model.addAttribute("trades", tradeRepository.findAll());
        redirectAttributes.addFlashAttribute("success", "Trade got deleted");
        return "redirect:/trade/list";
    }
}
