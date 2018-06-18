package advance1.entity;

import java.time.LocalDate;

public class BookingRequest {

    private LocalDate localDate;

    private Hotel hotel;

    public BookingRequest(LocalDate localDate, Hotel hotel) {
        this.localDate = localDate;
        this.hotel = hotel;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
