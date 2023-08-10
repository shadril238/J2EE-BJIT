package interfaceexample;

public class Food extends EatableAbstract implements IEatable, IDrinkable {

//    private Food(){
//
//    }
//    public static Food createInstance(){
//        return new Food();
//    }
    public void food(){
        System.out.println("Opps, I am food (General)!...");
    }
    @Override
    public void other() {
        System.out.println("I am eating food!");
    }

    @Override
    void anotherFromAbstract() {
        System.out.println("Another method from abstract!");
    }

    @Override
    public void anotherMethod() {
        System.out.println("Another method from interface!");
    }

    @Override
    public void drink() {
        System.out.println("I am drinking!");
    }

    @Override
    public void sip() {
        System.out.println("I am sipping!");
    }
}
