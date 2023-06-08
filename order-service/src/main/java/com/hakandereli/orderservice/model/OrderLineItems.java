package com.hakandereli.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "t_order_line_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItems {
    //Identity mysql veritabanındaki auto_increment a eştir.
    //oracle olsaydı sequence olabilir di.
    //table ayrı bir tabloda tutar
    //auto ise jpa sağlayıcısına bağlı otomatik seçilir.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
