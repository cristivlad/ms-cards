package com.example.mscards.controller;

import com.example.mscards.config.CardsServiceConfig;
import com.example.mscards.model.Cards;
import com.example.mscards.model.Customer;
import com.example.mscards.model.Properties;
import com.example.mscards.repository.CardsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardsController {

    private final CardsRepository cardsRepository;
    private final CardsServiceConfig cardsConfig;

    public CardsController(CardsRepository cardsRepository, CardsServiceConfig cardsConfig) {
        this.cardsRepository = cardsRepository;
        this.cardsConfig = cardsConfig;
    }

    @PostMapping("/myCards")
    public List<Cards> getCardDetails(@RequestBody Customer customer) {
        return cardsRepository.findByCustomerId(customer.getCustomerId());
    }

    @GetMapping("/cards/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(cardsConfig.getMsg(), cardsConfig.getBuildVersion(),
                cardsConfig.getMailDetails(), cardsConfig.getActiveBranches());
        return ow.writeValueAsString(properties);
    }
}
