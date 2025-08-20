package com.dmarv.microservice.accounts.service.impl;

import com.dmarv.microservice.accounts.constants.AccountsConstants;
import com.dmarv.microservice.accounts.dto.CustomerDto;
import com.dmarv.microservice.accounts.entity.Accounts;
import com.dmarv.microservice.accounts.entity.Customer;
import com.dmarv.microservice.accounts.exception.CustomerAlreadyExistsException;
import com.dmarv.microservice.accounts.mapper.CustomerMapper;
import com.dmarv.microservice.accounts.repository.AccountsRepository;
import com.dmarv.microservice.accounts.repository.CustomerRepository;
import com.dmarv.microservice.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer =
                customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("A Customer was already registered" +
                    " with the given mobile number:  " + customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));

    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");
        return newAccount;
    }

}
