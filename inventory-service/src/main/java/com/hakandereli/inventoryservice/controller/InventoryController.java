package com.hakandereli.inventoryservice.controller;

import com.hakandereli.inventoryservice.dto.InventoryResponse;
import com.hakandereli.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    //Bu şekilde çağrı yaparsan her stok kodu için tek tek http isteği yapıcak bu maliyet demek bunu tek sefer de yapmalısın.
//    @GetMapping("/{sku-code}")
//    @ResponseStatus(HttpStatus.OK)
//    public boolean isInStock(@PathVariable("sku-code") String skuCode) {
//        return inventoryService.isInStock(skuCode);
//    }

    //Örneğin kırmızı iphone 13 ile iphone 13 için farklı sorgular yerine tek sorguda.
    //http://localhost:8082/api/inventory?skuCode=iphone-13&skucode=iphone13-red
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCodeList){
        return inventoryService.isInStockList(skuCodeList);
    }

    //@RequestParam
//    http://localhost:8080/movies/search?name=saw
//    http://localhost:8080/movies/search?name=saw&country=ABD

//    Bu tarz requestler için uygundur soru işaretinden sonra gelenleri okur.
//
//
//    @PathVariable
//    http://localhost:8080/movies/1/movies/{movieId}
//    http://localhost:8080/movies/1/stars/movies/{movieId}/stars
//    http://localhost:8080/movies/1/stars/2/movies/{movieId}/stars/{starId}

    //Kalıp yapılarda pathvaribale mantıklı

//    Eğer @RequestParam parametresini isteğe bağlı yapmak isterseniz, required özelliğini false yapabilirsiniz.
//    Default değer atamak isterseniz, defaultValue özelliğini kullanabilirsiniz.


}
