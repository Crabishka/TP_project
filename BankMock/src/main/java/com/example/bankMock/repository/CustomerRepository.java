package com.example.bankMock.repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // методы для работы с данными клиентов
}
