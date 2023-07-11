package com.practica1.backend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CartDTO {
    @NotNull(message = "The idCustomer field is required")
    private Long idCustomer;

    private List<CartProductDTO> products;
}