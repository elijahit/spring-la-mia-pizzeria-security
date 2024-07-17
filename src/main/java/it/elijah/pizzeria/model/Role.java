package it.elijah.pizzeria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Role {

  @Id
  private Integer id;

  @NotNull
  private String name;
}
