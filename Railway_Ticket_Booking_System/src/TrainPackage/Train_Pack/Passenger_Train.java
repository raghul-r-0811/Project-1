package TrainPackage.Train_Pack;

import java.util.Scanner;

public class Passenger_Train implements Train{
    String train_name;
    String train_id;
    String starting_point;
    String destination;
    private static final Scanner scanner = new Scanner(System.in);
    @Override
    public void registerTrain() {
        System.out.println("Give a name for the Train");
        train_name = scanner.nextLine();
        System.out.println();
    }
}
