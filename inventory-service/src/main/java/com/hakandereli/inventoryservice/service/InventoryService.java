package com.hakandereli.inventoryservice.service;

import com.hakandereli.inventoryservice.dto.InventoryResponse;
import com.hakandereli.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    //readOnly = true parametresi, işlemin sadece veritabanından okuma işlemlerini içerdiğini belirtir.
//    @Transactional(readOnly = true)
//    public boolean isInStock(String skuCode){
//        //ispresent boşmu diye kontrol eder.
//        return inventoryRepository.findBySkuCode(skuCode).isPresent();
//    }

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStockList(List<String> skuCodeList) {
        return inventoryRepository.findBySkuCodeIn(skuCodeList).stream()
                .map(inventory ->
                    InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity() > 0)
                            .build()
                ).toList();
    }
}
