package interfaceexample;

public interface Eatable {
    default void eat(){
        System.out.println("I am eating!");
    }
    void other();
    void anotherMethod();
}
