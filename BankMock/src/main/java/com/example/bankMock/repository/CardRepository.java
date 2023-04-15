package com.example.bankMock.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    // методы для работы с данными банковских карт
}
