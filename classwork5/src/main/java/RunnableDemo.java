

// can implement multiple interfaces + extend 1 class

public class RunnableDemo implements Runnable{


    String name;

    RunnableDemo(String name){
        this.name = name;

    }
    public static void main(String[] args) throws InterruptedException {
        RunnableDemo r = new RunnableDemo("A");
        RunnableDemo r2 = new RunnableDemo("B");
        // You can pass the same runnable object to multiple threads
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();



        t1.join();// dont move to line 22 until t1.run finishes
        t2.join(); // sleep check loop until other thread is done
        System.out.println("main thread done.");
        // with lambda
        var myThread = new Thread (() -> { System.out.println("this is my thread logic!"); });
        myThread.start();
    }

    @Override
    public void run(){
        //This will in a different thread
        System.out.println("I am in a thread " + name);


    }
}
