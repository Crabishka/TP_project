package com.example.bankMock.service;

import com.example.bankMock.model.Card;
import com.example.bankMock.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public boolean isValidCard(Card card) {
        // проверить данные карты
        // вернуть true, если данные действительны, иначе false
    }

    // другие методы для работы с данными банковских карт
}
