package interfaceexample;

public class Apple extends Food{
    public void apple(){
        System.out.println("Opps, I am apple!..");
    }
    @Override
    public void sip(){
        System.out.println("Apple can't be sipped!");
    }
}
