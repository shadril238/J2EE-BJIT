package model;

import jakarta.annotation.PreDestroy;

public class Trainee {
    private int id;

    public Trainee() {
        System.out.println("Default constructor called - Trainee");
    }

    public Trainee(int id) {
        this.id = id;
        System.out.println("Parameterized constructor called - Trainee");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @PreDestroy
    public void destroy() {
        System.out.println("Trainee object destroyed.");
    }
}
