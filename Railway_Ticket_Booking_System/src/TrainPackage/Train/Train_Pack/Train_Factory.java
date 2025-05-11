//package TrainPackage.Train.Train_Pack;
//
//import TrainPackage.Train.Train_Pack.Builder.GoodsTrain_Builder;
//import TrainPackage.Train.Train_Pack.Builder.PassengerTrain_Builder;
//import TrainPackage.Train.Train_Pack.Builder.Train_Builder;
//
//import java.util.Scanner;
//
//public class Train_Factory {
//    private static final Scanner scanner = new Scanner(System.in);
//    public static Train createTrain() {
//        System.out.println("enter Train type that u want to create");
//        System.out.println("Should be either Goods or Passenger");
//
//        String trainType = scanner.nextLine();
//        while (!trainType.equalsIgnoreCase("goods") && !trainType.equalsIgnoreCase("passenger")) {
//            System.out.println("Invalid train type! Please enter either 'Goods' or 'Passenger':");
//            trainType = scanner.nextLine().trim();
//        }
//        Train_Builder newTrain;
//        if(trainType.equalsIgnoreCase("goods")){
//            newTrain = new GoodsTrain_Builder();
//        }else{
//            newTrain = new PassengerTrain_Builder();
//        }
//        // add the train to the db before returning the object.
//        newTrain.registerTrain();
//        return newTrain;
//    }
//
//
//}
