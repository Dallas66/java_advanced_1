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
        logger.info("Synchronized queue");
        synchronized (this) {
            logger.info("Checking operations counter");
            if (hotelCounterAdd.incrementAndGet() <= 15) {
                try {
                    while (list.size() == 5) {
                        logger.info("Queue is full, waiting");
                        wait();
                    }
                    list.add(bookingRequest);
                    logger.info(Thread.currentThread().getName() + " sent request " + list.getLast() + " " + getHotelCounterAdd().toString() + "\n");
                    logger.info(Thread.currentThread().getName() + " Queue state after sent " + list);
                } catch (InterruptedException e) {
                    logger.error("InterruptedException " + e.getMessage());
                } finally {
                    this.notifyAll();
                }
            } else {
                return;
            }
        }
    }

    public void get() {
        logger.info("Synchronized queue");
        synchronized (this) {
            logger.info("Checking operations counter");
            if (hotelCounterGet.incrementAndGet() <= 15) {
                try {
                    while (list.isEmpty()) {
                        logger.info("Queue is empty, waiting");
                        wait();
                    }
                    logger.info(Thread.currentThread().getName() + " received request " + list.getFirst() + "\n");
                    list.removeFirst();
                    logger.info(Thread.currentThread().getName() + " Queue state after recived " + list);
                    Thread.sleep(5000);
                    logger.info(Thread.currentThread().getName() + " processed request" + "\n");
                } catch (InterruptedException e) {
                    logger.error("InterruptedException " + e.getMessage());
                } finally {
                    this.notifyAll();
                }
            } else {
                return;
            }
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
