package com.github.nhatoriginal.spring.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
@JsonIgnoreProperties({ "hashedPassword", "carts", "orders", "reviews", "eateries", "addresses"})
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id")
  private UUID id;

  @Email
  @Column(name = "email", nullable = false, unique = true)
  private String email;
  @Column(name = "hashed_password", nullable = false)
  private String hashedPassword;

  @Column(name = "full_name", nullable = false)
  private String fullName;

  @Column(name = "date_of_birth", nullable = false)
  private LocalDate dateOfBirth;

  @Enumerated(EnumType.STRING)
  @Column(name = "gender", nullable = false)
  private Gender gender;

  @Enumerated(EnumType.STRING)
  @Column(name = "role", nullable = false)
  private Role role;

  @Column(name = "phone", nullable = false)
  private String phone;

  @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
  @JoinTable(name = "user_addresses", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
  private List<Address> addresses;

  @OneToMany(mappedBy = "owner")
  @JsonIncludeProperties({"id", "name", "phone",  "isAlive" })
  private List<Eatery> eateries;
  @OneToMany(mappedBy = "user")
  private List<Order> orders;
  @JsonIncludeProperties({"id", "rating", "comment" })
  @OneToMany(mappedBy = "user")
  private List<Review> reviews;
  @OneToMany(mappedBy = "user")
  @JsonIgnoreProperties({ "user" })
  private List<Cart> carts;

}
