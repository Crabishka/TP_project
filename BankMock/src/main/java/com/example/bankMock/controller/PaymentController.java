package com.example.bankMock.controller;

import com.example.bankMock.model.Card;
import com.example.bankMock.model.Invoice;
import com.example.bankMock.service.CardService;
import com.example.bankMock.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PaymentController {
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private CardService cardService;

    @PostMapping("/pay")
    public String pay(@ModelAttribute("card") Card card, Model model) {
        // проверить информацию о карте
        boolean validCard = cardService.isValidCard(card);
        // добавить информацию о карте в модель
        model.addAttribute("card", card);
        if (validCard) {
            // оплатить заказ
            Invoice invoice = invoiceService.pay(card);
            // добавить информацию об оплате в модель
            model.addAttribute("invoice", invoice);
            return "checkout";
        } else {
            // карта недействительна, вернуть на страницу оплаты с сообщением об ошибке
            model.addAttribute("error", "Invalid card information.");
            return "payment";
        }
    }
}

