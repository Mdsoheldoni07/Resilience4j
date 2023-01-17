package com.catalog.catalogservice;

import com.catalog.catalogservice.entity.Orders;
import com.catalog.catalogservice.repository.OrderRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
@RequestMapping("/api/v1")
public class CatalogServiceApplication {


	@Autowired
   private OrderRepository oooooooooorderRepository;

	@GetMapping("orders")
	public List<Orders> getAllOrders(){
		return this.orderRepository.findAll();
	}

	@PostMapping("orders")
	public Orders addOrders(@RequestBody Orders orders){

		return this.orderRepository.save(orders);

	}


//	@GetMapping("/getAllOrders/{name}")
//	public ResponseEntity<Orders> getOdrsByProductName(@PathVariable(value = "name") String name)
//			throws Exception
//	{
//		Orders orders = (Orders) orderRepository.findByName(name);
//				return ResponseEntity.ok().body(orders);
//	}


//	@PostConstruct
//	public void initOrdersTable() {
//		orderRepository.saveAll(Stream.of(
//						new Orders("mobile", "electronics", "white", 2000),
//						new Orders("T-Shirt", "clothes", "black", 999),
//						new Orders("Jeans", "clothes", "blue", 1999),
//						new Orders("Laptop", "electronics", "gray", 50000),
//						new Orders("digital watch", "electronics", "black", 2500),
//						new Orders("Fan", "electronics", "black", 50000)
//				).
//				collect(Collectors.toList()));
//	}

//	@GetMapping
//	public List<Orders> getOrders(){
//		return orderRepository.findAll();
//	}
	@GetMapping("orders/{category}")
	public List<Orders> getOrdersByCategory(@PathVariable String category){
		return orderRepository.findByCategory(category);
	}

	@GetMapping("ordersdisplay/{name}")
	public List<Orders>  getOdrsByProductName(@PathVariable String name){
		return orderRepository.findByName(name);
	}

	public static void main(String[] args) {
		SpringApplication.run(CatalogServiceApplication.class, args);
	}

}
