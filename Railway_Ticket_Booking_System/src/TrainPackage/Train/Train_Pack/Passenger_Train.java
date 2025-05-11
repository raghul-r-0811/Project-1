package TrainPackage.Train.Train_Pack;

//import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import DAO.Train.PassengerTrain_DAO;
import TrainPackage.Train.Compartment_Package.Compartment_type;
import TrainPackage.Train.Train_Pack.Builder.PassengerTrain_Builder;
import TrainPackage.Train.Train_Pack.Builder.Train_Builder;

public class Passenger_Train implements Train{
    String train_name;
    String train_id;
    String starting_point;
    String destination;
    Map<Compartment_type,Integer> compartments ;
    private static final Scanner scanner = new Scanner(System.in);


    public Passenger_Train(PassengerTrain_Builder ptb){ //ptb -  passenger_train_builder
        this.train_id = ptb.getTrain_id();
        this.train_name = ptb.getName();
        this.starting_point = ptb.getStarting_point();
        this.destination = ptb.getDestination();
        this.compartments = ptb.getCompartments();

    }

    @Override
    public void registerTrain() {
        // mostly redundant. Mostly managed by builder. Can use it if we need it later.
        //✅ Approach 2: Keep start_station and end_station as "Derived" Fields
        // You store them in train, but they are not manually inserted. Instead, you set them based on the train_route table.
        //https://chatgpt.com/c/67c2eb1a-78d0-800c-a608-2f51c88dfc46



        // passing this class to train builder

        //once builder is done.
        //PassengerTrain_DAO passengerTrain_dao = new PassengerTrain_DAO();
        //passengerTrain_dao.registerTrain(this);

    }

    @Override
    public String getTrain_id() {
        return this.train_id;
    }
}
