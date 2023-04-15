package com.example.bankMock.controller;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        // добавить информацию о заказе в модель
        model.addAttribute("invoice", new Invoice());
        return "home";
    }
}
