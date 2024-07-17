package it.elijah.pizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.elijah.pizzeria.model.Ingredient;


public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

  public Ingredient findByIngredientIgnoreCase(String ingredient);

}
