package com.hakandereli.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hakandereli.productservice.dto.ProductRequest;
import com.hakandereli.productservice.model.ProductResponse;
import com.hakandereli.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.6");

	//Mock mvc postman istekleri taklit etmek için kullanılır.
	//@AutoConfigureMockMvc anatasyonu olması gerekir
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	ProductRepository productRepository;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    void shouldCreateProduct() throws Exception {

		ProductRequest productRequest = getProductRequest();
		String productRequestString = objectMapper.writeValueAsString(productRequest);

		//request i hazırlıyoruz ancak .content methodu string tip istiyor
		//productrequest to json yada tam tersini yapmak için ObjectMapper kullandım.
		//andexpect ile kontrol ediyoruz statusu created mi diye status methodu static import MockMvcResultMatchers.status dır
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequestString))
				.andExpect(status().isCreated());
		//Aşağıda test ürününü ekleyip eklemediğini kontrol eder 1 kayıt yoksa test başarısızdır ve testi geçemez uygulama.
		Assertions.assertEquals(1, productRepository.findAll().size());
    }

	//istek tipi product request olmalı bu yüzden bu tipte bir obje oluşturdum.
	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("Iphone 13")
				.description("iphone 13")
				.price(BigDecimal.valueOf(1200))
				.build();
	}

	/*
	Yukarıdaki kodlar bir entegrasyon testi aslında proje ayağa kalkarken docker tabanlı mongodb containera bağlantı
	sağlanabiliyormu diye kontrol etmek için yazıldı.

	Görünen o ki, ProductServiceApplicationTests adında bir test sınıfınız var. Bu sınıf @SpringBootTest ve
	 @Testcontainers anotasyonlarıyla işaretlenmiş.

@SpringBootTest anotasyonu, Spring Boot uygulamanızı başlatarak genel bir test ortamı oluşturmanızı sağlar.
Bu anotasyon sayesinde, Spring Boot uygulamanızın bileşenlerini test etmek ve uygulamanın tam olarak yüklendiğinden
 emin olmak için gerekli olan altyapıyı sağlayabilirsiniz.

@Testcontainers anotasyonu ise Docker tabanlı konteynerlerin kullanımını testlerinizde sağlar. Bu durumda,
 MongoDBContainer adında bir test konteyneri tanımlanmış ve mongo:4.4.22 imajının kullanılmasını sağlamışsınız.
  Bu konteyneri MongoDB veritabanınızı test etmek için kullanabilirsiniz.

@Container anotasyonu, test konteynerini bir statik alanda tanımlamanızı sağlar. Bu durumda mongoDBContainer,
 MongoDBContainer sınıfından oluşturulmuş bir statik değişken olarak belirtilmiş.

@DynamicPropertySource anotasyonu, dinamik bir özellik kaynağı tanımlamanızı sağlar. Bu özellik, test çalıştırıldığında
 MongoDB bağlantı URL'sini belirlemek için kullanılıyor. dynamicPropertyRegistry üzerinden spring.data.mongodb.uri
  özelliğini mongoDBContainer nesnesinden alınan replika set URL'siyle tanımlıyorsunuz.

Son olarak, contextLoads() adında bir test metodu tanımlanmış. Bu metot, uygulamanın başarıyla yüklendiğini doğrulamak
 için kullanılıyor.

Bu test sınıfı, Spring Boot uygulamanızın yükleme sürecini ve MongoDB veritabanını test etmek için Docker tabanlı
 bir konteyneri kullanma yöntemini gösteriyor.
	* */

}
