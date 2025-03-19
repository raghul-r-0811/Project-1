package TrainPackage.Passenger.Train_Pack;

import java.util.Scanner;

public class Train_Factory {
    private static final Scanner scanner = new Scanner(System.in);
    public static Train createTrain() {
        System.out.println("enter Train type that u want to create");
        System.out.println("Should be either Goods or Passenger");

        String trainType = scanner.nextLine();
        while (!trainType.equalsIgnoreCase("goods") && !trainType.equalsIgnoreCase("passenger")) {
            System.out.println("Invalid train type! Please enter either 'Goods' or 'Passenger':");
            trainType = scanner.nextLine().trim();
        }
        Train newTrain;
        if(trainType.equalsIgnoreCase("goods")){
            newTrain = new GoodsTrain();
        }else{
            newTrain = new Passenger_Train();
        }
        // add the train to the db before returning the object.
        newTrain.registerTrain();
        return newTrain;
    }


}
