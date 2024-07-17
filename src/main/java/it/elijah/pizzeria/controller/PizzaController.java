package it.elijah.pizzeria.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import it.elijah.pizzeria.model.Pizza;
import it.elijah.pizzeria.model.SpecialOffers;
import it.elijah.pizzeria.repository.IngredientRepository;
import it.elijah.pizzeria.repository.PizzaRepository;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/")
public class PizzaController {

	@Autowired
	private PizzaRepository repository;

	@Autowired
	private IngredientRepository ingredientRepository;

	@GetMapping
	public String index(Model model, @RequestParam(name = "search", required = false) String search) {

		List<Pizza> pizze = new ArrayList<>();

		if (search == null || search.isBlank()) {
			pizze = repository.findAll();
		} else {
			pizze = repository.findByNomeIgnoreCase(search);
		}

		model.addAttribute("pizze", pizze);

		return "index";
	}

	@GetMapping("/pizza/{id}")
	public String show(@PathVariable("id") Integer pizzaId, Model model) {

		model.addAttribute("pizza", repository.getReferenceById(pizzaId));

		return "/pizza/index";
	}

	@GetMapping("/create")
	public String createForm(Model model) {
		model.addAttribute("pizza", new Pizza());
		model.addAttribute("ingredients", ingredientRepository.findAll());

		return "/create/index";

	}

	@PostMapping("/create")
	public String responseForm(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			return "/create/index";
		}

		repository.save(formPizza);

		return "redirect:/";

	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer pizzaId, Model model) {
		model.addAttribute("pizza", repository.getReferenceById(pizzaId));
		model.addAttribute("ingredients", ingredientRepository.findAll());

		return "/edit/index";
	}

	@PostMapping("/edit/{id}")
	public String update(
			@Valid @ModelAttribute("pizza") Pizza pizza,
			BindingResult bindingResult,
			Model model) {
		
		if(bindingResult.hasErrors()) {
			return "/edit/index";
		}
		
		repository.save(pizza);
		
		return "redirect:/";
	}

	@PostMapping("/delete/{id}")
	public String delete(
			@ModelAttribute("pizza") Pizza pizza,
			Model model) {

		repository.delete(pizza);
		
		return "redirect:/";
	}

	@GetMapping("/pizza/{id}/offers")
  public String offers(@PathVariable("id") Integer id, Model model) {
    Pizza pizza = repository.findById(id).get();
    SpecialOffers offers = new SpecialOffers();
    offers.setStartDate(LocalDate.now());
    offers.setPizza(pizza);

    model.addAttribute("offers", offers);

    return "offers/create";
  }
	
}
