//package com.shadril.dependencyinjection.config;
//
//import com.shadril.dependencyinjection.model.Calculation;
//import com.shadril.dependencyinjection.model.Person;
//import jakarta.annotation.PreDestroy;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//
//@Configuration
//public class BeanConfig {
//    public BeanConfig() {
//        System.out.println("BeanConfig is created.");
//    }
//
//    @Bean
//    @Scope("prototype")
//    public Calculation calculation(){
//        return new Calculation();
//    }
//    @Bean
//    public Person person1(Calculation calculation){
//        return new Person("Shadril", calculation);
//    }
//    @Bean
//    public Person person2(Calculation calculation){
//        return new Person("Mery", calculation);
//    }
//    @PreDestroy
//    public void destroy(){
//        System.out.println("BeanConfig is destroyed");
//    }
//}
