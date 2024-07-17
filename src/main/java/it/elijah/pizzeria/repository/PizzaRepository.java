package it.elijah.pizzeria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.elijah.pizzeria.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
	
	@Query("Select p from Pizza p where p.nome like %:nome%")
	public List<Pizza> findByNomeIgnoreCase(String nome);
}
