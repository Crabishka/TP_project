package com.example.bankMock.model;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Card card;

    // геттеры и сеттеры
}
