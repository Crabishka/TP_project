package com.example.bankMock.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Invoice {
    private Long id;
    private BigDecimal amount;
    private LocalDateTime date;

    public Invoice(Long id, BigDecimal amount, LocalDateTime date) {
        this.id = id;
        this.amount = amount;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}