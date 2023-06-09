package com.hakandereli.inventoryservice.repository;

import com.hakandereli.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

//    Optional<Inventory> findBySkuCode(String skuCode);

    List<Inventory> findBySkuCodeIn(List<String> skuCodeList);


    /*
    * Inventory nesnesini döndüren bir metodun imzasını temsil ediyor gibi görünmektedir.
    *  Bu imza, bir SKU (Stok Kontrol Birimi) koduna göre bir envanter kaydını bulmayı amaçlar.

    *Optional sınıfı, Java 8'den itibaren gelen bir sınıftır ve null değerleri ele almak için kullanılır.
    *  Bir metot Optional tipi döndürdüğünde, gerçek sonucun olmayabileceğini ifade eder.
    *  Bu durumda, metot bir sonuç döndürmek yerine, sonucu sarmalayan bir Optional nesnesini döndürür.
    *  Bu sayede, kullanıcılar null kontrolü yapmadan sonuç üzerinde işlem yapabilir.
    * */
}
