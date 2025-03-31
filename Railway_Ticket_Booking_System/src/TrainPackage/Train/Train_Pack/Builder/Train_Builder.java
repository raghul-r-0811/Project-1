package TrainPackage.Train.Train_Pack.Builder;

import TrainPackage.Train.Compartment_Package.Compartment_type;
import TrainPackage.Train.Train_Pack.Train;

import java.util.Map;

public interface Train_Builder {
    Train_Builder setTrainName(String name);
    Train_Builder setStartingPoint(String start);
    Train_Builder setDestination(String destination);
    Train_Builder setCompartments(Map<Compartment_type,Integer> compartments); // just for storing the info about the compartments
    Train_Builder addCompartments(String train_id); // the actual fuction where adding compartments into DB will start
    Train build();
}
