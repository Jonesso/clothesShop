package com.clothesshop.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
public class User implements Serializable {

  private static final long serialVersionUID = 4887904943282174032L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NaturalId
  @NotEmpty
  private String email;
  @NotEmpty
  @Size(min = 7, message = "Password must be at least 7 symbols long")
  private String password;
  @NotEmpty
  private String name;
  @NotEmpty
  private String phone;
  @NotEmpty
  private String address;
  @NotNull
  private boolean active;
  @NotEmpty
  private String role = "ROLE_CUSTOMER";

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonIgnore
  private Cart cart;


  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", name='" + name + '\'' +
        ", phone='" + phone + '\'' +
        ", address='" + address + '\'' +
        ", active=" + active +
        ", role='" + role + '\'' +
        '}';
  }

}
