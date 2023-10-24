package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
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
public class BidListController {

    @Autowired
    private BidListRepository bidListRepository;

    @RequestMapping("/bidList/list")
    public String home(Model model) {
        model.addAttribute("bidLists", bidListRepository.findAll());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(Model model) {
        model.addAttribute("bidList", new BidList());
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(
            @Valid BidList bid,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {
        if(!result.hasErrors()){
            bidListRepository.save(bid);
            model.addAttribute("bidLists", bidListRepository.findAll());
            redirectAttributes.addFlashAttribute("success", "BidList got saved");
            return "redirect:/bidList/list";
        }
        redirectAttributes.addFlashAttribute("error", "BidList could not get saved");
        return "redirect:/bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListRepository.findById(id).orElseThrow(() -> new IllegalStateException("Invalid bidList id " + id));
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(
            @PathVariable("id") Integer id,
            @Valid BidList bidList,
            RedirectAttributes redirectAttributes,
            BindingResult result,
            Model model) {
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("error", "BidList could not get updated");
            return "bidList/update";
        }
        bidList.setBidListId(id);
        bidListRepository.save(bidList);
        model.addAttribute("bidLists", bidListRepository.findAll());
        redirectAttributes.addFlashAttribute("success", "BidList got updated");
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(
            @PathVariable("id") Integer id,
            RedirectAttributes redirectAttributes,
            Model model) {
        BidList bidList = bidListRepository.findById(id).orElseThrow(() -> new IllegalStateException("Invalid bidList id " + id));
        bidListRepository.delete(bidList);
        model.addAttribute("bidList", bidList);
        redirectAttributes.addFlashAttribute("success", "BidList got deleted");
        return "redirect:/bidList/list";
    }
}
