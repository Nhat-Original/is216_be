package com.github.nhatoriginal.spring.model;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "menus")
@JsonIgnoreProperties({"eatery"})
public class Menu {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id")
  private UUID id;

  @OneToOne
  @JoinColumn(name = "eatery_id", nullable = false)
  private Eatery eatery;
  @JsonBackReference
  @OneToMany(mappedBy = "menu", fetch = FetchType.EAGER)
  private List<MenuItem> menuItems;
}