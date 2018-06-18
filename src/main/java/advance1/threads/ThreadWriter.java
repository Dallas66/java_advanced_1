package advance1.threads;

import advance1.customToolse.CustomQueue;
import advance1.entity.BookingRequest;
import advance1.entity.Hotel;

import java.time.LocalDate;

public class ThreadWriter extends Thread {
    private CustomQueue customQueue;

    public ThreadWriter(CustomQueue customQueue) {
        this.customQueue = customQueue;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            if (customQueue.getHotelCounterAdd().intValue() < 15) {
                BookingRequest request = new BookingRequest(LocalDate.now(), new Hotel(15, "adad", 3));
                customQueue.add(request);
                System.out.println(Thread.currentThread().getName() + " sent ");
            } else interrupt();
        }
    }
}

