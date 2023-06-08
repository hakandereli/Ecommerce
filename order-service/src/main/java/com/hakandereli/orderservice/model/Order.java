package com.hakandereli.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="t_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItems> orderLineItemsList;

    /*
        CascadeType.ALL:
    Tüm işlemleri (ekleme, güncelleme, silme) ilişkili varlıklara yayarak tam kaskad işlem yapar.
    Ana varlık üzerinde yapılan herhangi bir işlem (örneğin, ekleme, güncelleme, silme), ilişkili varlıklara da yansır.
    Dikkatli kullanılmalıdır, çünkü istenmeyen sonuçlara yol açabilir.

        CascadeType.PERSIST:
    Ekleme işlemlerini (persist) ilişkili varlıklara yayarak kaskad ekleme yapar.
    Ana varlık üzerinde yapılan bir ekleme işlemi, ilişkili varlıklara da yansır.
    Güncelleme veya silme işlemlerini etkilemez.

            CascadeType.MERGE:
    Güncelleme işlemlerini (merge) ilişkili varlıklara yayarak kaskad güncelleme yapar.
    Ana varlık üzerinde yapılan bir güncelleme işlemi, ilişkili varlıklara da yansır.
    Ekleme veya silme işlemlerini etkilemez.

            CascadeType.REMOVE:
    Silme işlemlerini (remove) ilişkili varlıklara yayarak kaskad silme yapar.
    Ana varlık üzerinde yapılan bir silme işlemi, ilişkili varlıklara da yansır.
    Ekleme veya güncelleme işlemlerini etkilemez.

            CascadeType.REFRESH:
    Varlık yenileme işlemini (refresh) ilişkili varlıklara yayarak kaskad yenileme yapar.
    Ana varlık üzerinde yapılan bir yenileme işlemi, ilişkili varlıklara da yansır.
    Ekleme, güncelleme veya silme işlemlerini etkilemez.

            CascadeType.DETACH:
    Varlığı ilişkili varlıklardan ayırarak kaskad ayırma işlemi yapar.
    Ana varlık üzerinde yapılan bir ayrılma işlemi, ilişkili varlıklara da yansır.
    Ekleme, güncelleme veya silme işlemlerini etkilemez.

     */

}
