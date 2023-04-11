package com.example.bankMock.model;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String expirationMonth;
    private String expirationYear;
    private String cvv;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // геттеры и сеттеры
}
