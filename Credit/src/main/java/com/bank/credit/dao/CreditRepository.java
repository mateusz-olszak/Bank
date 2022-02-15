package com.bank.credit.dao;

import com.bank.credit.domain.Credit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CreditRepository extends CrudRepository<Credit, Integer> {
}
