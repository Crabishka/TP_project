package com.example.bankMock.service;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public Invoice pay(Card card) {
        // выполнить оплату заказа с использованием данных карты
        // вернуть информацию об оплате в виде объекта Invoice
    }

    // другие методы для работы с данными чеков
}
