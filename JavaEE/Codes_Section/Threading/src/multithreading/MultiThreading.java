package multithreading;

public class MultiThreading {
    public static void main(String[] args) {

        for(int i = 1; i < 5; i++){
            MultiThreadingUnit runnableObj = new MultiThreadingUnit(i);
            Thread threadObj = new Thread(runnableObj);
            threadObj.start();
            //threadObj.run();
        }
    }
}
