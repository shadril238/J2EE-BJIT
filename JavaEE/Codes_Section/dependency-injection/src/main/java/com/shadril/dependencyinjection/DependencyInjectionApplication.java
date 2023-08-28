package com.shadril.dependencyinjection;

import com.shadril.dependencyinjection.model.Calculation;
import com.shadril.dependencyinjection.model.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DependencyInjectionApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DependencyInjectionApplication.class, args);
//		Calculation c = new Calculation();
//		Person p1 = new Person("Shadril", c);
//		p1.printResult();
//		Person p = context.getBean(Person.class);
//		p.printResult();
	}

}
