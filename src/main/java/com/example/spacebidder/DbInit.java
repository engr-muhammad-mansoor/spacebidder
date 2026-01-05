package com.example.spacebidder;

import com.example.spacebidder.repository.ClientRepository;
import com.example.spacebidder.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbInit implements CommandLineRunner
{

    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public DbInit(ProductRepository productRepository, ClientRepository clientRepository) {
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public void run(String... args) throws Exception
    {
        /*productRepository.saveAll(List.of(
                new Product( )));


        clientRepository.saveAll(List.of(
                new User()));*/

    }
}