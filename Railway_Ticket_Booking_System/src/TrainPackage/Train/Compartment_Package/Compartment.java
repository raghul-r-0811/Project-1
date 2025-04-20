package TrainPackage.Train.Compartment_Package;

import DAO.Train.CompartmentDAO;
import TrainPackage.Train.Seat_Pack.Seat;
import TrainPackage.Train.Seat_Pack.SeatType;

import java.util.ArrayList;
import java.util.List;

public class Compartment {
    //
    Compartment_type compartmentType;  // sleeper, general, AC
    String compartmentName;   // S1-n
    List<Seat> seatList;
    String trainId;

    public Compartment(Compartment_type type, String name,String trainId){
        this.compartmentName = name;
        this.compartmentType = type;
        this.seatList = new ArrayList<>();
        fillSeats();
        this.trainId = trainId;
    }

    private void fillSeats() {
        //based on compartment type fill the list of seats.
        Compartment_type temp = this.compartmentType;
        if(temp == Compartment_type.AC || temp == Compartment_type.SLEEPER){
            System.out.println("Filling seats for Sleeper and AC ");
            for(int i =1;i<=50;i++){
                Seat currentSeat;
                if(i%5 == 1){
                     currentSeat = new Seat(i,SeatType.AC_LOWER);
                }else if(i%5 == 2){
                    currentSeat = new Seat(i,SeatType.AC_MIDDLE);
                }else if(i%5 == 3){
                    currentSeat = new Seat(i,SeatType.AC_UPPER);
                }else if(i%5 == 4){
                    currentSeat = new Seat(i,SeatType.AC_SIDE_LOWER);
                }else{
                    currentSeat = new Seat(i,SeatType.AC_SIDE_UPPER);
                }
                this.seatList.add(currentSeat);
            }
        }else {
            System.out.println("Filling seats for General and SecondClass");
            for(int i =1;i<=100;i++){
                Seat currentSeat = new Seat(i,SeatType.SECOND_CLASS_SEATER);
                this.seatList.add(currentSeat);
            }
        }
    }

    public Compartment_type getCompartmentType() {

        return compartmentType;
    }

    public void setCompartmentType(Compartment_type compartmentType) {
        this.compartmentType = compartmentType;
    }

    public String getCompartmentName() {
        return compartmentName;
    }

    public void setCompartmentName(String compartmentName) {
        this.compartmentName = compartmentName;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<Seat> seatList) {
        this.seatList = seatList;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }
//    public void addToDB(){
//        CompartmentDAO compartmentDAO = new CompartmentDAO();
//        compartmentDAO.addCompartment(this);
//    }

}
