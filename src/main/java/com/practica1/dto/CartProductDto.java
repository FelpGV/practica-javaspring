package com.practica1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartProductDto {
    private Long idProduct;
    private Integer quantity;
}
