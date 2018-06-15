package threads;

import customToolse.CustomQueue;

import java.util.Stack;

public class ThreadReader extends Thread {

    CustomQueue customQueue;

    public ThreadReader(CustomQueue customQueue) {
        this.customQueue = customQueue;
    }

    @Override
    public void run() {
        customQueue.get();
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
