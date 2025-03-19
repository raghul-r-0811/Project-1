package TrainPackage.Passenger.Train_Pack;

//import java.util.List;
import java.util.Map;
import java.util.Scanner;
import DAO.Train.PassengerTrain_DAO;
import TrainPackage.Passenger.Compartment_Package.*;
public class Passenger_Train implements Train{
    String train_name;
    String train_id;
    String starting_point;
    String destination;
    Map<Compartment_type,Integer> compartments ;
    private static final Scanner scanner = new Scanner(System.in);




    @Override
    public void registerTrain() {

        //✅ Approach 2: Keep start_station and end_station as "Derived" Fields
        // You store them in train, but they are not manually inserted. Instead, you set them based on the train_route table.
        //https://chatgpt.com/c/67c2eb1a-78d0-800c-a608-2f51c88dfc46


        System.out.println("Give a name for the Train");
        train_name = scanner.nextLine();
        System.out.println("Starting point of the train");
        starting_point = scanner.nextLine();
        System.out.println("Destination of the train");
        destination = scanner.nextLine();



        //once builder is done.
        PassengerTrain_DAO passengerTrain_dao = new PassengerTrain_DAO();
        passengerTrain_dao.registerTrain(this);

    }
}
