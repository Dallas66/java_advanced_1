package advance1.threads;

import advance1.customToolse.CustomQueue;

public class ThreadReader extends Thread {

    private CustomQueue customQueue;

    public ThreadReader(CustomQueue customQueue) {
        this.customQueue = customQueue;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            if (customQueue.getHotelCounterGet().intValue() < 15) {
                customQueue.get();
                System.out.println(Thread.currentThread().getName() + " received ");
                try {
                    sleep(5000);
                    System.out.println(Thread.currentThread().getName() + " processed");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else interrupt();
        }
    }
}
