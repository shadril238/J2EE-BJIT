package interfaceexample;

public class Food extends EatableAbstract implements Eatable{

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

    public static void main(String[] args) {
        EatableAbstract eatableAbstractObj = new Food();
        eatableAbstractObj.eatingAbstract();
        eatableAbstractObj.other();
        eatableAbstractObj.anotherFromAbstract();
        //eatableAbstractObj.food(); -->> not possible

        Eatable eatableObj = new Food();
        eatableObj.eat();
        eatableObj.other();
        eatableObj.anotherMethod();
        //eatableObj.food(); -->> not possible

        Food foodObj = new Food();
        foodObj.food();
    }
}
