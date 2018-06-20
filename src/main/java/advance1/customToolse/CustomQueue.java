package advance1.customToolse;

import advance1.entity.BookingRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class CustomQueue {
    private final Logger logger = LoggerFactory.getLogger(CustomQueue.class);
    private LinkedList list;
    private AtomicInteger hotelCounterAdd = new AtomicInteger(0);
    private AtomicInteger hotelCounterGet = new AtomicInteger(0);


    public CustomQueue() {
        this.list = new LinkedList();
    }

    public void add(BookingRequest bookingRequest) {
        synchronized (this) {
            logger.info("Synchronized queue" + "\n");
            try {
                while (list.size() == 5) {
                    logger.info("Queue is full, waiting" + "\n");
                    wait();
                }
                if (hotelCounterAdd.intValue() < 15) {
                    hotelCounterAdd.incrementAndGet();
                    list.add(bookingRequest);
                    logger.info("Checking add counter " + hotelCounterAdd);
                    logger.info("Sent request " + list.getLast() + " " + getHotelCounterAdd().toString());
                    logger.info("Queue state after sent " + list.size() + "\n");
                } else return;
            } catch (InterruptedException e) {
                logger.error("InterruptedException " + e.getMessage());
            } finally {
                this.notifyAll();
            }
        }
    }

    public void get() {
        synchronized (this) {
            logger.info("Synchronized queue" + "\n");

            if (hotelCounterGet.intValue() < 15) {
                try {
                    while (list.isEmpty()) {
                        logger.info("Queue is empty, waiting" + "\n");
                        wait();
                    }
                    hotelCounterGet.incrementAndGet();
                    logger.info("Checking get counter " + hotelCounterGet);
                    logger.info("Received request " + list.getFirst());
                    list.removeFirst();
                    logger.info("Queue state after recived " + list.size() + "\n");
                } catch (InterruptedException e) {
                    logger.error("InterruptedException " + e.getMessage());
                } finally {
                    this.notifyAll();
                }
            } else return;
        }
    }

    public List getList() {
        return list;
    }

    @Override
    public String toString() {
        return "CustomQueue{" +
                "hotelCounterAdd=" + hotelCounterAdd +
                '}';
    }

    public AtomicInteger getHotelCounterAdd() {
        return hotelCounterAdd;
    }

    public AtomicInteger getHotelCounterGet() {
        return hotelCounterGet;
    }

    public Logger getLogger() {
        return logger;
    }
}
