// can't extend more classes

public class ThreadDemo extends Thread {
    String name;


    ThreadDemo(String name){
        this.name = name;

    }


    public static void main(String[] args) {
        // Thread 1
        ThreadDemo thread1 = new ThreadDemo("A");
        ThreadDemo thread2 = new ThreadDemo("B");
       // thread1.run(); // Don't do this
        thread1.start(); // "tell os to queue this up to run eventually"
        thread2.start();
        System.out.println("main thread done.");
    }

        @Override
        public void run(){
            //This will in a different thread
            System.out.println("I am in a thread " + name);


    }
}
