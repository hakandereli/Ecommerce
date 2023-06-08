package com.hakandereli.inventoryservice.service;

import com.hakandereli.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    //readOnly = true parametresi, işlemin sadece veritabanından okuma işlemlerini içerdiğini belirtir.
    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode){
        //ispresent boşmu diye kontrol eder.
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }
}
