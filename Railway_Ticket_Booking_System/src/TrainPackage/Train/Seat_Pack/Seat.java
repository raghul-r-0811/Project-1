package TrainPackage.Train.Seat_Pack;

enum SeatStatus{
    AVAILABLE,
    BOOKED,
    CANCELLED,
    WAITLISTED;
}
public class Seat {
    int st_num;
    SeatType st_type;
    SeatStatus st_stat;

    public Seat(int i, SeatType seatType) {
        this.st_num = i;
        this.st_type = seatType;
        this.st_stat = SeatStatus.AVAILABLE;
    }

    public int getSt_num() {
        return st_num;
    }

    public void setSt_num(int st_num) {
        this.st_num = st_num;
    }

    public SeatType getSt_type() {
        return st_type;
    }

    public void setSt_type(SeatType st_type) {
        this.st_type = st_type;
    }

    public SeatStatus getSt_stat() {
        return st_stat;
    }

    public void setSt_stat(SeatStatus st_stat) {
        this.st_stat = st_stat;
    }
}
