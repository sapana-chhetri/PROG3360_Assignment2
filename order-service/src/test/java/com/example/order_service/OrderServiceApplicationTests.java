package com.example.order_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class OrderServiceApplicationTests {

    private final RestTemplate restTemplate = new RestTemplate();

	@Test
	void contextLoads() {
	}

//    @Test
//    public void badRequestTest(){
//        String result = restTemplate.postForObject("http://localhost:8082/orders", null, String.class);
//        assert (result == "");
//    }

}
