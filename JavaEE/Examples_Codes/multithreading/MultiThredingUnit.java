package multithreading;

public class MultiThredingUnit implements Runnable{

    int threadNumber;
    public MultiThredingUnit(int threadNumber){
        this.threadNumber = threadNumber;
    }

    @Override
    public void run(){
        for(int i=1; i<6; i++){
            System.out.println("Thread value: "+i+ ", Thread Number: "+ threadNumber);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
