package br.com.fiap.techchallenge.diegopinho.msstorage.dtos;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.fiap.techchallenge.diegopinho.msstorage.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ProductDTO {

  @JsonProperty
  @NotBlank(message = "name is required and cannot be blank")
  private String name;

  @JsonProperty
  @NotBlank(message = "name is required and cannot be blank")
  private String description;

  @JsonProperty
  @Positive
  @NotBlank(message = "price is required and cannot be blank")
  private BigDecimal price;

  @JsonProperty
  @PositiveOrZero
  @NotBlank(message = "price is required and cannot be blank")
  private int quantity;

  public Product toProduct() {
    Product product = new Product();
    product.setName(this.name);
    product.setDescription(this.description);
    product.setPrice(this.price);
    product.setQuantity(this.quantity);

    return product;
  }

}
