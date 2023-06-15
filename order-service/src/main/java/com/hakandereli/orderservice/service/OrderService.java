package com.hakandereli.orderservice.service;

import com.hakandereli.orderservice.dto.InventoryResponse;
import com.hakandereli.orderservice.dto.OrderLineItemsDto;
import com.hakandereli.orderservice.dto.OrderRequest;
import com.hakandereli.orderservice.model.Order;
import com.hakandereli.orderservice.model.OrderLineItems;
import com.hakandereli.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);


        //Burada siparişteki tüm ürünleri stock kodlarını alıyorum bir listeye.
        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode).toList();


        //webClient.get(): WebClient kullanarak GET isteği oluşturur. WebClient,
        // Spring WebFlux çerçevesi tarafından sağlanan blok olmayan bir HTTP istemcisi.
        //
        //.uri("http://localhost:8082/api/inventory"): GET isteğinin URI'sini belirler.
        // Bu durumda "http://localhost:8082/api/inventory" endpointine istek yapılıyor.
        //
        //.retrieve(): GET isteğini gerçekleştirmek için WebClient'ı kullanır ve bir BodySpec nesnesini döndürür.
        // Bu nesne, yanıtı almak için çeşitli işlemleri yapmanıza olanak tanır.
        //
        //.bodyToMono(Boolean.class): Yanıtı bir Mono nesnesine dönüştürür.
        // Mono, asenkron bir şekilde dönen tek bir değeri temsil eder. Bu durumda, Boolean tipinde bir değer bekleniyor.
        //
        //.block(): Mono nesnesini bloklayarak yanıtı bekler ve döndürür.
        // Bu yöntem, asenkron akışın tamamlanmasını bekleyerek kodun ilerlemesini engeller ve sonucu döndürür.
        //senkron çalışmasını istediğim için block yazdım.


        //Siparişteki ürünlerin stock kodları ile inventory-service den sorgulama yapıyorum stok varmı bu ürünlerde diye.
        InventoryResponse[] inventoryResponsArray = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",uriBuilder -> uriBuilder.queryParam("skuCodeList",skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        //buradan dönen sonuç iphone13 var, samsung galaxy yok gibi dönüyor.

        //Allmacth metodu içerisinde bir if gibi çalışır hepsi true dönüyorsa true döner
        boolean allProductsInStock = Arrays.stream(inventoryResponsArray).allMatch(InventoryResponse::isInStock);

        // TODO: 6/9/2023 stock azaltma eklenecek
        //Ürünleri tümü stokta varsa siparişi kaydet
        if (allProductsInStock) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }


        log.info("Order {} is saved", order.getId());
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();

        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        return orderLineItems;
    }
}
