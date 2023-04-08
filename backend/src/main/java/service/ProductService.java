package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Order;
import entity.Product;
import repository.ProductRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {

    private ProductRepository productRepository;
    public Order getAllProducts() throws Exception {//а почему сразу не возвращаем лист. Фигня какая-то
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new Exception("No products found");
        }
        return (Order) products;
    }


    public Product getProductById(Long id) {
       Product product = productRepository.findById(id);
        return product;

    }

    public Map<Integer, Boolean> getProductSizes(String date) {
        Map<Integer, Boolean> sizeMap = new HashMap<>();
        List<Product> products = productRepository.findByDate(date); // Получение списка продуктов на указанную дату
        for (Product product : products) {

        }
        return null;
    }

    public List<LocalDate> getEmployedDates(int size) {
        return null;
    }

   /* public String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        return result.toString();
    }*/
}
