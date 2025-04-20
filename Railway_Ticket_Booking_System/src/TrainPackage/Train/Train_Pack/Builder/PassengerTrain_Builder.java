package TrainPackage.Train.Train_Pack.Builder;

import DAO.Train.CompartmentDAO;
import TrainPackage.Train.Compartment_Package.Compartment;
import TrainPackage.Train.Compartment_Package.Compartment_type;
import TrainPackage.Train.Train_Pack.Passenger_Train;
import TrainPackage.Train.Train_Pack.Train;
import DAO.Train.PassengerTrain_DAO;

import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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



    // here is where compartment builder will be called
    // should be called after train has been added to the DB
    // use the data from the map to add compartments to the DB.
    // call compartment factory.
    @Override
    public void addCompartments(String train_id) {

        List<Compartment> compartmentList = new ArrayList<>();
        int num = 1;
        for(Map.Entry<Compartment_type,Integer> entry : this.compartments.entrySet() ){
            for(int i=1;i<=entry.getValue();i++){
                Compartment_type current = entry.getKey();
                String compName = "";
                if( current == Compartment_type.AC){
                    compName = "AC"+num++;
                }else if(current == Compartment_type.GENERAL){
                    compName = "G"+num++;
                }else if(current == Compartment_type.SLEEPER){
                    compName = "S"+num++;
                } else if (current== Compartment_type.TwoS) {
                    compName = "T"+num++;
                }
                Compartment compartment = new Compartment(current,compName,train_id);
                //adding newly created compartment into the list
                compartmentList.add(compartment);
            }
        }
        CompartmentDAO compartmentDAO = new CompartmentDAO();
        System.out.println("passing the list to compartmentDAO");
        compartmentDAO.addCompartment(compartmentList);
    }
    // this setC
    @Override
    public Train_Builder setCompartments(Map<Compartment_type, Integer> compartments) {
        this.compartments = compartments;
        return this;
    }

    @Override
    public Train_Builder setDestination(String destination) {
        this.destination = destination;
        return this;
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

        System.out.println("checking destination = "+destination);
        this.train_id = passengerTrain_dao.registerTrain(this.name,this.starting_point,this.destination);
        System.out.println("in passengerTrain_builder.build this.train_id "+this.train_id);
         System.out.println("did i  get the id");
        return new Passenger_Train(this);
    }
}
