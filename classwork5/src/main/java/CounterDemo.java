import java.sql.SQLOutput;
import java.util.concurrent.atomic.AtomicInteger;

public class CounterDemo implements Runnable{


    int counter = 0;
    AtomicInteger aCounter = new AtomicInteger(0);

    public static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        var r = new CounterDemo();
        var t1 = new Thread(r);
        var t2 = new Thread(r);


        t1.start();
        t2.start();

        t1.join();
        t2.join();

       // System.out.println(r.counter);
        System.out.println(r.aCounter.get());
    }

        // Only allow 1 thread to access this method at the same time
        public synchronized void inc(){
        counter ++;
        aCounter.incrementAndGet();
        }

    @Override
    public void run() {
        for(int i = 0 ; i < 100000; i++){
           // counter++;

            synchronized (lock){
                // only 1 thread can access this per the lock object
                counter++;
                aCounter.incrementAndGet();
            }

            inc();

        }
        System.out.println("thread done");
    }
}
