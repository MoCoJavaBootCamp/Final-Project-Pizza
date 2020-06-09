//package com.example.demo;
//
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class FrequentToppingService {
//
//	private final FrequentToppingRepo frequentToppingRepo;
//
//	public FrequentToppingService(FrequentToppingRepo frequentToppingRepo) {
//		this.frequentToppingRepo = frequentToppingRepo;
//	}
//
//	public Pizza findMostCommonTopping() {
//		List<FrequentToppings> frequentToppings = frequentToppingRepo.findToppingCount();
//		return frequentToppings.get(0).getCar();
//	}
//
//}
