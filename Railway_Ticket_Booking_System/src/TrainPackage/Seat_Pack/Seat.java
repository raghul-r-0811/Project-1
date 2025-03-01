package TrainPackage.Seat_Pack;

enum SeatStatus{
    AVAILABLE,
    BOOKED,
    CANCELLED,
    WAITLISTED;
}
public class Seat {
    String st_num;
    SeatType st_type;
    SeatStatus st_stat;
}
