package interfaceexample;

public class Animal extends MoveableAbstract implements Moveable{
    @Override
    public void other() {
       System.out.println("I am in Other Method");
    }
    @Override
    public void anotherMethod() {
       System.out.println("I am in Another Method");
    }
    @Override
    public void otherMethodFromAbstract(){
        System.out.println("I am from Abstract Class");
    }

    public static void main(String[] args){
        MoveableAbstract moveAbstractObj = new Animal();
        moveAbstractObj.other();
        moveAbstractObj.moveinAbstract();
        moveAbstractObj.moveLikeSRK();

        Moveable moveableObj = new Animal();
        moveableObj.move();
        moveableObj.anotherMethod();
        moveableObj.other();

        Animal animalObj = new Animal();
        animalObj.other();

    }
}
