package interfaceexample;

public class Water extends Food{
    @Override
    public void sip(){
        System.out.println("Water can't be sipped!");
    }
    @Override
    public void drink() {
        System.out.println("Yeooo,,, Drink water as more as you can!!!");
    }
    public void water(){
        System.out.println("Hello, I am water!");
    }
}
