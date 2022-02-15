package com.bank.customer.dao;

import com.bank.customer.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    List<Customer> findAllByPesel(String pesel);
    List<Customer> findAllByIdIn(List<Integer> id);
}
