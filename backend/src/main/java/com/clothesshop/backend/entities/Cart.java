package com.clothesshop.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Cart implements Serializable {

  @Id
  @NotNull
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long cartId;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JsonIgnore
  private User user;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "cart")
  private Set<ItemInOrder> items = new HashSet<>();

  @Override
  public String toString() {
    return "Cart{" +
        "cartId=" + cartId +
        ", items=" + items +
        '}';
  }

  public Cart(User user) {
    this.user = user;
  }
}
