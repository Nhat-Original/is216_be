package com.github.nhatoriginal.spring.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id")
  private UUID id;

  @Column(name = "rating", nullable = false)
  private int rating;

  @Column(name = "comment", nullable = false)
  private String comment;

  @ManyToOne()
  @JsonIgnoreProperties({ "reviews" })
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "menu_item_id", nullable = false)
  private MenuItem menuItem;
}
