package DAO.Train;


import TrainPackage.Train.Train_Pack.Train;

public interface TrainDAO {
    String registerTrain(String train_name,String starting_point, String destination);
}
