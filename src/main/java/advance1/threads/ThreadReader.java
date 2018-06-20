package advance1.threads;

import advance1.customToolse.CustomQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadReader extends Thread {

    private Logger logger = LoggerFactory.getLogger(ThreadReader.class);


    private CustomQueue customQueue;

    public ThreadReader(CustomQueue customQueue) {
        this.customQueue = customQueue;
    }

    @Override
    public void run() {
        while (customQueue.getHotelCounterGet().intValue() < 15) {
            customQueue.get();
            logger.info("Processed request" + "\n");
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
