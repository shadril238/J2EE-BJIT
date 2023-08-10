package interfaceexample;

public class MainClass {
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
        foodObj.sip();
        foodObj.eat();
        foodObj.other();

        Food foodObj1 = new Apple();
        foodObj1.eat();
        foodObj1.anotherFromAbstract();
        foodObj1.sip();
        //foodObj1.food(); -->> not possible

        Apple appleObj = new Apple();
        appleObj.sip();
        appleObj.apple();
        appleObj.anotherMethod();

        Water water = new Water();
        water.drink();
        water.sip();

        Food waterObj = new Water();
        //waterObj.water(); -->> not possible
        waterObj.drink();
        waterObj.sip();
    }
}
