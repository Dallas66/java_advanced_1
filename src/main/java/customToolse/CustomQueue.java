package customToolse;

import entity.BookingRequest;

import java.util.LinkedList;
import java.util.List;


public class CustomQueue {
    private List list;
    private int count = 0;

    public CustomQueue() {
        this.list = new LinkedList();
    }

    public void add(BookingRequest bookingRequest) {

        synchronized (this) {
            try {
                if (list.size() == 5) {
                    wait();
                }

                list.add(bookingRequest);
                count++;

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                notifyAll();
            }
        }
    }

    public BookingRequest get() {
        BookingRequest bookingRequest = null;
        synchronized (this) {
            try {
                if (list.size() == 0) {
                    wait();
                }else {
                    bookingRequest = (BookingRequest) list.get(0);
                    list.remove(0);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                notifyAll();
            }
        }
        return bookingRequest;
    }

    public List getList() {
        return list;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "CustomQueue{" +
                "count=" + count +
                '}';
    }
}
