package com.example.bankMock.repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    // методы для работы с данными чеков
}
