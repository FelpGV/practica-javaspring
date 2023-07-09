package com.practica1.model.repository;

import com.practica1.dto.CustomerSpendDTO;
import com.practica1.model.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT new com.practica1.dto.CustomerSpendDTO(c.idCustomer, c.name, SUM(i.total)) " +
            "FROM Customer c JOIN c.invoice i " +
            "GROUP BY c.idCustomer")
    List<CustomerSpendDTO> calculateTotalSpendForAllCustomers();

    @Query("SELECT new com.practica1.dto.CustomerSpendDTO(c.idCustomer, c.name, SUM(i.total)) " +
            "FROM Customer c JOIN c.invoice i " +
            "GROUP BY c.idCustomer")
    Page<CustomerSpendDTO> getCustomerSpendPaginate(Pageable pageable);

    Optional<Customer> findByEmail(String email);
}
