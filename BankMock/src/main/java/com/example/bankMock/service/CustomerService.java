package com.example.bankMock.service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    // другие методы для работы с данными клиентов
}
