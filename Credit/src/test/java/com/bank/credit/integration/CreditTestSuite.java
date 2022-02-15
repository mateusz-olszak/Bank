package com.bank.credit.integration;

import com.bank.credit.dao.CreditRepository;
import com.bank.credit.domain.Credit;
import liquibase.pro.packaged.L;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CreditTestSuite {

    @Autowired
    private CreditRepository creditRepository;

    @Test
    void test_createCredit() {
        // Given
        Credit credit = new Credit(1,"Hipoteczny",125000);
        // When
        Credit savedCredit = creditRepository.save(credit);
        // Then
        int id = savedCredit.getId();
        assertEquals(1,id);
        // Clean up
        creditRepository.deleteById(id);
    }

    @Test
    void test_getCredits() {
        // Given
        Credit credit1 = new Credit(1,"Hipoteczny",125000);
        Credit credit2 = new Credit(2,"Hipoteczny",125000);
        Credit credit3 = new Credit(1,"Hipoteczny",125000);
        Credit savedCredit1 = creditRepository.save(credit1);
        Credit savedCredit2 = creditRepository.save(credit2);
        Credit savedCredit3 = creditRepository.save(credit3);
        int savedCredit1Id = savedCredit1.getId();
        int savedCredit2Id = savedCredit2.getId();
        int savedCredit3Id = savedCredit3.getId();
        // When
        List<Credit> resultList = (List<Credit>) creditRepository.findAll();
        // Then
        assertEquals(3, resultList.size());
        // Clean up
        creditRepository.deleteById(savedCredit1Id);
        creditRepository.deleteById(savedCredit2Id);
        creditRepository.deleteById(savedCredit3Id);
    }

}
