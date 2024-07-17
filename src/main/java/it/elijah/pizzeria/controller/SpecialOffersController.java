package it.elijah.pizzeria.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.elijah.pizzeria.model.SpecialOffers;
import it.elijah.pizzeria.repository.SpecialOffersRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/offers")
public class SpecialOffersController {

  @Autowired
  private SpecialOffersRepository specialOffersRepository;
  
  @GetMapping("/{id}/edit")
  public String offersEdit(@PathVariable("id") Integer id, Model model) {

    model.addAttribute("offers", specialOffersRepository.findById(id).get());

    return "offers/edit";
  }

  @PostMapping("/{id}/edit")
  public String offersEditPost(@Valid @ModelAttribute("offers") SpecialOffers offers, BindingResult bindingResult,
      Model model) {
    if (bindingResult.hasErrors()) {
      return "/offers/edit";
    }

    specialOffersRepository.save(offers);

    return "redirect:/pizza/" + offers.getPizza().getId();
  }

  @PostMapping("/create")
  public String offersCreate(@Valid @ModelAttribute("offers") SpecialOffers offers, BindingResult bindingResult,
      Model model) {
    if (bindingResult.hasErrors()) {
      return "/offers/create";
    }

    specialOffersRepository.save(offers);

    return "redirect:/pizza/" + offers.getPizza().getId();
  }
}
