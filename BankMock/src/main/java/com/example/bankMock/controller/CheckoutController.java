package com.example.bankMock.controller;

@Controller
public class CheckoutController {
    @GetMapping("/checkout")
    public String showCheckout(Model model) {
        // create order details
        String orderId = "123456";
        String orderDate = "2023-04-10";
        String orderTotal = "$100.00";

        // create customer information and card information
        Customer customer = new Customer();
        Card card = new Card();
        customer.setCard(card);

        // add data to the model
        model.addAttribute("orderId", orderId);
        model.addAttribute("orderDate", orderDate);
        model.addAttribute("orderTotal", orderTotal);
        model.addAttribute("customer", customer);

        return "checkout";
    }

    @PostMapping("/checkout")
    public String processCheckout(@ModelAttribute("customer") Customer customer, Model model) {
        // validate card and customer information
        boolean isValid = true;
        String error = null;

        // perform payment processing logic
        // ...

        // if payment was successful, redirect to success page
        if (isValid) {
            return "redirect:/success";
        } else {
            error = "There was an error processing your payment. Please try again.";
            model.addAttribute("error", error);
            return "checkout";
        }
    }

    @GetMapping("/success")
    public String showSuccess() {
        return "success";
    }
}
