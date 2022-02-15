package com.bank.credit.service;

import com.bank.credit.dao.CreditRepository;
import com.bank.credit.domain.Credit;
import com.bank.credit.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditService {

    private final CreditRepository creditRepository;
    private final CustomerService customerService;

    @Autowired
    public CreditService(CreditRepository creditRepository, CustomerService customerService) {
        this.creditRepository = creditRepository;
        this.customerService = customerService;
    }

    public CreditNumberDto createCredit(CreditDto creditDto) {
        CustomerClientDto customerDto = customerService.searchCustomer(creditDto.getCustomer());
        if (customerDto != null) {
            Credit credit = new Credit(customerDto.getCustomerId(),creditDto.getCreditName(),creditDto.getValue());
            return new CreditNumberDto(creditRepository.save(credit).getId());
        } else {
            CustomerIdDto customerId = customerService.createCustomer(creditDto.getCustomer());
            Credit credit = new Credit(customerId.getCustomerId(),creditDto.getCreditName(),creditDto.getValue());
            return new CreditNumberDto(creditRepository.save(credit).getId());
        }
    }

    public List<CreditDto> getCredits() {
        final List<Credit> creditsDb = (List<Credit>) creditRepository.findAll();
        final List<Integer> customersIds = creditsDb.stream().map(Credit::getCustomerId).collect(Collectors.toList());
        final List<CustomerClientDto> customers = customerService.getCustomers(customersIds);
        final List<CreditDto> result = new ArrayList<>();
        for (Credit credit : creditsDb)
            for (CustomerClientDto customer : customers)
                if (credit.getCustomerId() == customer.getCustomerId())
                    result.add(new CreditDto(credit.getId(),credit.getCreditName(), credit.getValue(),
                            new CustomerDto(customer.getFirstName(), customer.getLastName(), customer.getPesel())));
        return result;
    }

}
