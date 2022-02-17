package com.bank.credit.client;

import com.bank.credit.config.CustomerConfig;
import com.bank.credit.dto.*;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CustomerClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerClient.class);

    private static final String CREATE_CUSTOMER = "customer";
    private static final String SEARCH_CUSTOMER = "customer/search";
    private static final String FILTER_CUSTOMER = "customer/filtered";

    private final RestTemplate restTemplate;
    private final CustomerConfig customerConfig;

    @Autowired
    public CustomerClient(RestTemplate restTemplate, CustomerConfig customerConfig) {
        this.restTemplate = restTemplate;
        this.customerConfig = customerConfig;
    }

    public CustomerIdDto createCustomer(CustomerDto customerDto) {
        LOGGER.info("starting to create new customer");
        URI url = UriComponentsBuilder.fromHttpUrl(customerConfig.getCustomerPath() + CREATE_CUSTOMER)
                .build().encode().toUri();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, String> params = new HashMap<>();
            params.put("firstName", customerDto.getFirstName());
            params.put("lastName", customerDto.getLastName());
            params.put("pesel", customerDto.getPesel());
            HttpEntity<Map<String, String>> entity = new HttpEntity<>(params, headers);
            return restTemplate.postForObject(url, entity, CustomerIdDto.class);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public List<CustomerClientDto> searchCustomer(CustomerDto customerDto) {
        LOGGER.info("searching for customer");
        URI url = UriComponentsBuilder.fromHttpUrl(customerConfig.getCustomerPath() + SEARCH_CUSTOMER)
                .build().encode().toUri();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, String> params = new HashMap<>();
            params.put("pesel", customerDto.getPesel());
            HttpEntity<Map<String,String>> entity = new HttpEntity<>(params,headers);
            CustomerSearchDto customerSearchDto = restTemplate.postForObject(url, entity, CustomerSearchDto.class);
            return Optional.ofNullable(customerSearchDto.getSearchResult())
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .stream().filter(p -> p.getPesel() != null)
                    .collect(Collectors.toList());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public List<CustomerClientDto> filterCustomers(List<Integer> customersIds) {
        LOGGER.info("filtering customers");
        URI url = UriComponentsBuilder.fromHttpUrl(customerConfig.getCustomerPath() + FILTER_CUSTOMER)
                .build().encode().toUri();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, List<Integer>> params = new HashMap<>();
            params.put("customersIds", customersIds);

            HttpEntity<Map<String,List<Integer>>> entity = new HttpEntity<>(params,headers);

            CustomerFilteredDto customerFilteredDto = restTemplate.postForObject(url, entity, CustomerFilteredDto.class);
            return Optional.ofNullable(customerFilteredDto.getCustomers())
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .stream().filter(p -> p.getPesel() != null)
                    .collect(Collectors.toList());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
