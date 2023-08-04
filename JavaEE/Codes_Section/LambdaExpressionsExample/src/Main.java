// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Printable lambdaPrintable = (suffix) -> System.out.println("Meow" + suffix);
        printThing(lambdaPrintable);
    }
    static void printThing(Printable thing){
        thing.print("!");
    }
}