package it.elijah.pizzeria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import it.elijah.pizzeria.model.Ingredient;
import it.elijah.pizzeria.repository.IngredientRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

  @Autowired
  private IngredientRepository ingredientRepository;

  @GetMapping
  public String index(Model model) {

    model.addAttribute("listaIngredienti", ingredientRepository.findAll());
    model.addAttribute("ingrediente", new Ingredient());

    return "/ingredients/index";

  }

  @PostMapping("/create")
  public String postMethodName(@Valid @ModelAttribute("ingrediente") Ingredient ingrediente,
      BindingResult bindingResult, Model model) {

    if (ingrediente.getIngredient() != null) {
      Ingredient ingredienteFiltrato = ingredientRepository.findByIngredientIgnoreCase(ingrediente.getIngredient());

      if (ingredienteFiltrato != null) {
        bindingResult
            .addError(new ObjectError("Errore di inserimento", "E' già presente un ingrediente con questo nome"));
      }

    }

    if (bindingResult.hasErrors()) {
      model.addAttribute("listaIngredienti", ingredientRepository.findAll());
      model.addAttribute("ingrediente", ingrediente);
      return "/ingredients/index";
    }

    ingredientRepository.save(ingrediente);
    return "redirect:/ingredients";
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable("id") Integer id) {

    ingredientRepository.delete(ingredientRepository.getReferenceById(id));

    return "redirect:/ingredients";
  }

}
