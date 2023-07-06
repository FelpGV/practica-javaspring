package com.practica1.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerSpendDTO {
    private Long idCustomer;
    private String name;
    private Double totalSpend;

    public CustomerSpendDTO(Long idCustomer, String name, Double totalSpend) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.totalSpend = totalSpend;
    }
}
