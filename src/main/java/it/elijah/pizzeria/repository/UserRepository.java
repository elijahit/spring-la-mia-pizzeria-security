package it.elijah.pizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.elijah.pizzeria.model.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

  @Query
  Optional<User> findByUsername(String username);

}
