package com.hakandereli.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


/*
* Yapılandırma Sınıfları: Spring projenizde yapılandırma ayarlarını tutan sınıfları tanımlamak için
* @Configuration anotasyonunu kullanabilirsiniz. Bu sınıflar genellikle @Bean anotasyonuyla işaretlenmiş metotlar içerir
* ve Spring konteyneri tarafından yönetilen nesnelerin yapılandırmasını sağlar.
* */
@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }
}
