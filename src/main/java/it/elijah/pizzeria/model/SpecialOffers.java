package it.elijah.pizzeria.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/*
  - una data di inizio
  - una data di fine
  - un titolo
 */

@Entity
@Table(name = "special_offers")
public class SpecialOffers {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @NotBlank
  @Column(nullable = false)
  private String title;
  
  @NotNull
  @Column(name = "start_date", nullable = false)
  private LocalDate startDate;
  
  @NotNull
  @Column(name = "end_date", nullable = false)
  private LocalDate endDate;
  
  @ManyToOne
  @JoinColumn(name = "pizza_id", nullable = false)
  private Pizza pizza;
  
  
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public String getTitle() {
    return title;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public LocalDate getStartDate() {
    return startDate;
  }
  
  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }
  
  public LocalDate getEndDate() {
    return endDate;
  }
  
  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }
  
  public Pizza getPizza() {
    return pizza;
  }
  
  public void setPizza(Pizza pizza) {
    this.pizza = pizza;
  }
}
