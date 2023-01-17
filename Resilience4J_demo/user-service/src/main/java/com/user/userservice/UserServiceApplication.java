package com.user.userservice;

import com.user.userservice.dto.OrdersDto;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
@RequestMapping("/user-service")
public class UserServiceApplication {

	@Autowired
	@Lazy
	private RestTemplate restTemplate;

	public static final String USER_SERVICE="userService";

	private static final String BASEURL = "http://localhost:8082/orders";

	private int attempt=1;


	@GetMapping("/displayOrders")
	// @CircuitBreaker(name =USER_SERVICE,fallbackMethod = "getAllAvailableProducts")
	@Retry(name = USER_SERVICE,fallbackMethod = "getAllAvailableProducts")
	public ArrayList displayOrders(@RequestParam("category") String category) {
		String url = category == null ? BASEURL : BASEURL + "/" + category;
		System.out.println("retry method called "+attempt++ +" times "+" at "+new Date());
 		return restTemplate.getForObject(url, ArrayList.class);
	}


	public List<OrdersDto> getAllAvailableProducts(Exception e){
		return Stream.of(
				new OrdersDto(119, "LED TV", "electronics", "white", 45000),
				new OrdersDto(345, "Headset", "electronics", "black", 7000),
				new OrdersDto(475, "Sound bar", "electronics", "black", 13000),
				new OrdersDto(574, "Puma Shoes", "foot wear", "black & white", 4600),
				new OrdersDto(678, "Vegetable chopper", "kitchen", "blue", 999),
				new OrdersDto(532, "Oven Gloves", "kitchen", "gray", 745)
		).collect(Collectors.toList());
	}

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
