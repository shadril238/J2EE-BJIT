package multithreading;

public class MultiThreading {

    public static void main(String args[]) {

        for (int i = 1; i < 5; i++) {
            MultiThredingUnit runnableObj = new MultiThredingUnit(i);
            Thread threadObj = new Thread(runnableObj);
            threadObj.start();
        }
    }
}
