package com.hakandereli.inventoryservice;

import com.hakandereli.inventoryservice.model.Inventory;
import com.hakandereli.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

//    Uygulama başlatırken veritabanına 2 veri ekler ancak her başlattığında ekler.
//    @Bean
//    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
//        return args -> {
//            Inventory inventory = new Inventory();
//            inventory.setSkuCode("iphone_13");
//            inventory.setQuantity(100);
//
//            Inventory inventory1 = new Inventory();
//            inventory1.setSkuCode("iphone_13_red");
//            inventory1.setQuantity(0);
//
//            inventoryRepository.save(inventory);
//            inventoryRepository.save(inventory1);
//        };
//    }
}
