package advance1.threads;

import advance1.customToolse.CustomQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadReader extends Thread {

    private Logger logger;


    private CustomQueue customQueue;

    public ThreadReader(CustomQueue customQueue) {
        this.customQueue = customQueue;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            if (customQueue.getHotelCounterGet().intValue() < 15) {
                customQueue.get();
            } else interrupt();
        }
    }
}
