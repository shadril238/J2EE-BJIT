package interfaceexample;

public interface IEatable {
    default void eat(){
        System.out.println("I am eating!");
    }
    void other();
    void anotherMethod();
}
