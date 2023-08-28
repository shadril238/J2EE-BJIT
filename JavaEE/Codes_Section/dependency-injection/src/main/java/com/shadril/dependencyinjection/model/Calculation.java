package com.shadril.dependencyinjection.model;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class Calculation {
    public Calculation(){
        System.out.println("Calculation bean is created.");
    }
    public void doSomeCalculation(){
        System.out.println("Doing Calculation");
        System.out.println("The result is - 300");
    }
    @PreDestroy
    public void destroy(){
        System.out.println("Calculation is destroyed");
    }
}
