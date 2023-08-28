package com.shadril.dependencyinjection.model;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class Person {
    private String name;
    private Calculation calculation;

    public Person() {

    }

    public Person(String name, Calculation calculation) {
        this.name = name;
        this.calculation = calculation;
        System.out.println("Person Bean is created.");
    }
    @PreDestroy
    public void destroy(){
        System.out.println("Person is destroyed");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calculation getCalculation() {
        return calculation;
    }

    public void setCalculation(Calculation calculation) {
        this.calculation = calculation;
    }

    public void printResult(){
        calculation.doSomeCalculation();
    }
}
