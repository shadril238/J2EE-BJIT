package model;

import jakarta.annotation.PreDestroy;

public class Academy {
    private int id;
    private String name;
    private String location;
    private Trainee trainee;

    public Academy() {
        System.out.println("Parameterized constructor called - Academy");
    }

    public Academy(int id, String name, String location, Trainee trainee) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.trainee = trainee;
        System.out.println("Parameterized constructor called - Academy");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Trainee getTrainee() {
        return trainee;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }
    @PreDestroy
    public void destroy(){
        System.out.println("Academy object destroyed.");
    }
}
