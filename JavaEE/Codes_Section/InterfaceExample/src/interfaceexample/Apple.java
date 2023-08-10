package interfaceexample;

public class Apple extends Food{
    public void apple(){
        System.out.println("Opps, I am apple!..");
    }
    public static void main(String[] args) {
        Food foodObj = new Apple();
        foodObj.eat();
        //foodObj.apple(); -->> not possible

        Apple appleObj = new Apple();
        appleObj.apple();

    }
}
