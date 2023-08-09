package multithreading;

public class MultiThreadingUnit implements Runnable{
    int threadNumber;
    public MultiThreadingUnit(int threadNumber){
        this.threadNumber = threadNumber;
    }
    @Override
    public void run(){
        for(int i = 1; i < 6; i++){
            System.out.println("Thread value: "+i+ ", Thread Number: "+ threadNumber);
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e){
                throw new RuntimeException();
            }
        }
    }
}
