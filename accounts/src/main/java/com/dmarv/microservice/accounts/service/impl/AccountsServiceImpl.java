package com.dmarv.microservice.accounts.service.impl;

import com.dmarv.microservice.accounts.dto.CustomerDto;
import com.dmarv.microservice.accounts.repository.AccountsRepository;
import com.dmarv.microservice.accounts.repository.CustomerRepository;
import com.dmarv.microservice.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {


    }
}
