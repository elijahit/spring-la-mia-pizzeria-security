package it.elijah.pizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.elijah.pizzeria.model.SpecialOffers;

public interface SpecialOffersRepository extends JpaRepository<SpecialOffers, Integer> {

}
