package threads;

import customToolse.CustomQueue;
import entity.BookingRequest;
import entity.Hotel;

import java.time.LocalDate;

public class ThreadWriter extends Thread {
    CustomQueue customQueue;
    Hotel hotel = new Hotel(15,"adad",3);
    LocalDate localDate = LocalDate.now();
    BookingRequest request = new BookingRequest(localDate,hotel);

    public ThreadWriter(CustomQueue customQueue) {
        this.customQueue = customQueue;
    }

    @Override
    public void run() {
        customQueue.add(request);
    }
}

