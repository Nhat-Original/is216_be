package com.github.nhatoriginal.spring.model;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "menu_items")
public class MenuItem {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id")
  private UUID id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "image_url", nullable = false)
  private String imageUrl;
  @JsonManagedReference
  @OneToMany(mappedBy = "menuItem")
  private List<MenuItemOption> menuItemOptions;
  @Column(name="is_delete", nullable = false)
  private boolean isDelete = false;
  @OneToMany(mappedBy = "menuItem")
  private List<Review> reviews;
  @JsonManagedReference
  @ManyToOne
  @JoinColumn(name = "menu_id", nullable = false)
  private Menu menu;
}
