package com.dmarv.microservice.accounts.service;

import com.dmarv.microservice.accounts.dto.CustomerDto;

public interface IAccountsService {
    void createAccount(CustomerDto customerDto);
}
