package TrainPackage.Train.Train_Pack.Builder;

import TrainPackage.Train.Compartment_Package.Compartment_type;
import TrainPackage.Train.Train_Pack.Passenger_Train;
import TrainPackage.Train.Train_Pack.Train;
import DAO.Train.PassengerTrain_DAO;

import java.nio.MappedByteBuffer;
import java.util.Map;

public class PassengerTrain_Builder implements Train_Builder{
    private String train_id;
    private String name;
    private String starting_point;
    private String destination;
    private Map<Compartment_type,Integer> compartments;

    @Override
    public Train_Builder setTrainName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Train_Builder setStartingPoint(String start) {
        this.starting_point = start;
        return this;
    }

    @Override
    public Train_Builder addCompartments(String train_id) {

        // here is where compartment builder will be called
        // should be called after train has been added to the DB
        // use the data from the map to add compartments to the DB.
        return null;
    }

    @Override
    public Train_Builder setCompartments(Map<Compartment_type, Integer> compartments) {
        this.compartments = compartments;
        return this;
    }

    @Override
    public Train_Builder setDestination(String destination) {
        return null;
    }

    public String getTrain_id() {
        return train_id;
    }

    public String getName() {
        return name;
    }

    public String getStarting_point() {
        return starting_point;
    }

    public String getDestination() {
        return destination;
    }

    public Map<Compartment_type, Integer> getCompartments() {
        return compartments;
    }

    @Override
    public Train build() {

        //call dao with the availabel data and generate id and get the id here and then call the actual Passenger Train class's constructor here.
        PassengerTrain_DAO passengerTrain_dao =  PassengerTrain_DAO.getInstance();
        this.train_id = passengerTrain_dao.registerTrain(this.name,this.starting_point,this.destination);
        return new Passenger_Train(this);
    }
}
