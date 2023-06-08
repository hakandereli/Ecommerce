package com.hakandereli.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    //Dışarıya açılacak objelerde modele eklenebilecek alanların hepsini açmak
    //Doğru olmayabilir bu sebeple request ve response için dto lar oluşturulmalıdır.
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
