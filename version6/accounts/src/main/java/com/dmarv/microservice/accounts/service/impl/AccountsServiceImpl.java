package com.dmarv.microservice.accounts.service.impl;

import com.dmarv.microservice.accounts.constants.AccountsConstants;
import com.dmarv.microservice.accounts.dto.AccountsDto;
import com.dmarv.microservice.accounts.dto.CustomerDto;
import com.dmarv.microservice.accounts.entity.Accounts;
import com.dmarv.microservice.accounts.entity.Customer;
import com.dmarv.microservice.accounts.exception.CustomerAlreadyExistsException;
import com.dmarv.microservice.accounts.exception.ResourceNotFoundException;
import com.dmarv.microservice.accounts.mapper.AccountsMapper;
import com.dmarv.microservice.accounts.mapper.CustomerMapper;
import com.dmarv.microservice.accounts.repository.AccountsRepository;
import com.dmarv.microservice.accounts.repository.CustomerRepository;
import com.dmarv.microservice.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));

    }

    // to update a users account information.
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer",
                        "mobileNumber", mobileNumber)
        );
        Accounts accounts =
                accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                        () -> new ResourceNotFoundException("Account",
                                "customerId", customer.getCustomerId().toString()));
        CustomerDto customerDto =
                CustomerMapper.mapToCustomerDto(customer,
                        new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(
                accounts, new AccountsDto()));

        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null) {
            Accounts accounts =
                    accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                            () -> new ResourceNotFoundException(
                                    "Account", "AccountNumber",
                                    accountsDto.getAccountNumber().toString())
                    );
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer =
                    customerRepository.findById(customerId).orElseThrow(
                            () -> new ResourceNotFoundException(
                                    "Customer", "CustomerID",
                                    customerId.toString())
                    );
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer =
                customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Customer", "MobileNumber", mobileNumber)
                );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

}
