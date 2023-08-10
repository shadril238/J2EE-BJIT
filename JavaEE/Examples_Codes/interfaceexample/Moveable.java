package interfaceexample;

public interface Moveable {

    default void move(){
        System.out.println("I am moving!");
    }
    void other();
    void anotherMethod();
}