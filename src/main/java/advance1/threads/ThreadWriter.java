package advance1.threads;

import advance1.customToolse.CustomQueue;
import advance1.entity.BookingRequest;
import advance1.entity.Hotel;

import java.time.LocalDate;
import java.util.Random;

public class ThreadWriter extends Thread {
    private CustomQueue customQueue;
    Random random = new Random();

    public ThreadWriter(CustomQueue customQueue) {
        this.customQueue = customQueue;
    }

    @Override
    public void run() {
        while (customQueue.getHotelCounterAdd().intValue() < 15) {
            BookingRequest request = new BookingRequest(LocalDate.now(), new Hotel(random.nextInt(19)+1, "Hotel", random.nextInt(4)+1));
            customQueue.add(request);
        }
    }
}

