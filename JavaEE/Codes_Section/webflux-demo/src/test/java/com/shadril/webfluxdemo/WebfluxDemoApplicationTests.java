package com.shadril.webfluxdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

@SpringBootTest
class WebfluxDemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void combineTwoStringFlux() {
		Flux<String> flux1 = Flux.just("Hello,", "Welcome To");
		Flux<String> flux2 = Flux.just("A", "new World!");

		// Using mergeWith
		Flux<String> mergeResult = flux1.mergeWith(flux2)
				.map(String::toUpperCase)
				.reduce((s1, s2) -> s1 + " " + s2)
				.flux()
				.onErrorResume(ex -> Flux.just("Error occurred: " + ex.getMessage())); // Handle errors

		// Using concatWith
		Flux<String> concatResult = flux1.concatWith(flux2)
				.map(String::toUpperCase)
				.reduce((s1, s2) -> s1 + " " + s2)
				.flux()
				.onErrorResume(ex -> Flux.just("Error occurred: " + ex.getMessage())); // Handle errors

		// Using zipWith
		Flux<String> zipResult = flux1.zipWith(flux2, (s1, s2) -> s1 + " " + s2)
				.map(String::toUpperCase)
				.reduce((s1, s2) -> s1 + " " + s2)
				.flux()
				.onErrorResume(ex -> Flux.just("Error occurred: " + ex.getMessage())); // Handle errors


		System.out.println("Using mergeWith:");
		mergeResult.subscribe(System.out::println);

		System.out.println("\nUsing concatWith:");
		concatResult.subscribe(System.out::println);

		System.out.println("\nUsing zipWith:");
		zipResult.subscribe(System.out::println);
	}

}
