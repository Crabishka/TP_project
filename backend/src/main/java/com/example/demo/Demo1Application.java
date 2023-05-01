package com.example.demo;

import com.example.demo.entity.ProductProperty;
import com.example.demo.entity.User;
import com.example.demo.repository.ProductPropertiesRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Demo1Application {

    private final UserRepository userRepository;
    private final ProductPropertiesRepository productPropertiesRepository;

    public Demo1Application(UserRepository userRepository, ProductPropertiesRepository productPropertiesRepository) {
        this.userRepository = userRepository;
        this.productPropertiesRepository = productPropertiesRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }


    @Bean
    public CommandLineRunner CommandLineRunnerBean() {
        return (args) -> {
            System.out.println("In CommandLineRunnerImpl ");

            userRepository.save(User.builder().id(1L).name("gosh").build());
            productPropertiesRepository.save(new ProductProperty(1L,129,"123","123"));
        };
    }

}
