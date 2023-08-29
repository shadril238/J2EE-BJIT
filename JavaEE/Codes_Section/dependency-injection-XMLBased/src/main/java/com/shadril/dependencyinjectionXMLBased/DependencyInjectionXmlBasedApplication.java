package com.shadril.dependencyinjectionXMLBased;

import model.Academy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:BeanConfig.xml"})
public class DependencyInjectionXmlBasedApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DependencyInjectionXmlBasedApplication.class, args);
		Academy academy1 = (Academy) context.getBean("academy1");
		System.out.println(academy1.getName());
	}

}
