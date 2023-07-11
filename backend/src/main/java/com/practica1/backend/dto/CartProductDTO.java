package com.practica1.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartProductDTO {
    @NotNull(message = "The idProduct field is required")
    private Long idProduct;
    @NotNull(message = "The quantity field is required")
    private Integer quantity;
}
