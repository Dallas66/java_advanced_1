package advance1.customToolse;

import advance1.entity.BookingRequest;


import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class CustomQueue {
    private LinkedList list;
    private AtomicInteger hotelCounterAdd = new AtomicInteger(0);
    private AtomicInteger hotelCounterGet = new AtomicInteger(0);


    public CustomQueue() {
        this.list = new LinkedList();
    }

    public void add(BookingRequest bookingRequest) {
        synchronized (this) {
            if (hotelCounterAdd.incrementAndGet() <= 15) {
                try {
                    while (list.size() == 5) {
                        wait();
                    }
                    list.add(bookingRequest);
                    System.out.println(getHotelCounterAdd());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    this.notifyAll();
                }
            } else {
                return;
            }
        }
    }

    public void get() {

        synchronized (this) {
            if (hotelCounterGet.incrementAndGet() <= 15) {
                try {
                    while (list.isEmpty()) {
                        wait();
                    }
                    list.removeFirst();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    this.notifyAll();
                }
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
}
