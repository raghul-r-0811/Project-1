package MainPack;

import java.util.*;

import DAO.Train.TrainDAO;
import DAO.Train.updateTrain;
import TrainPackage.Train.Compartment_Package.Compartment_type;
import TrainPackage.Train.Routes.trainStops;
import TrainPackage.Train.Ticket.TrainTicket;
import TrainPackage.Train.Train_Pack.Builder.PassengerTrain_Builder;
import TrainPackage.Train.Train_Pack.Builder.Train_Builder;
import TrainPackage.Train.Train_Pack.Train;
//import TrainPackage.Train.Train_Pack.Train_Factory;
import User.*;

import javax.security.auth.login.CredentialException;
// options for Admin user after login. Create train. Add routes. Update Profile
//options for General user -book ticket.View train.Update Profile.
public class Operations {
    public static final Scanner scanner = new Scanner(System.in);
    public Operations() {
        startApplication();
    }

    public static void startApplication(){
        System.out.println("Hello and welcome!");
        System.out.println("Login or Register");
        System.out.println("Press 1 if you are an existing user and want to Login");
        System.out.println("Press 2 if you are new and want to Register");
        Scanner scanner = new Scanner(System.in);
        try{
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    //login details
                    TrainTicket trainTicket = new TrainTicket();
                    trainTicket.checkTrainAvalibility();
                    break;
                case 2:
                    //Registering
                    // implementing factory method for creating regular user and Admin user
                    createUser();
                    break;
                default:
                    System.out.println("Invalid choice press either 1 or 2");
                    startApplication();
            }
        }catch(InputMismatchException e){
            System.out.println("Please enter a valid option!");
            startApplication();
        }
        catch(NoSuchElementException e){
            System.out.println("No input availabele");
        }catch(IllegalStateException e){
            System.out.println("Scanner is closed");
        }
    }
    public static void login(){

    }
    public static void createUser(){
        System.out.println("Admin or general to create the type of account");
        Scanner scanner = new Scanner(System.in);
        String userType = scanner.nextLine();
        UserFactory userFactory = null;
        if(userType.equalsIgnoreCase("Admin")){
            userFactory = new AdminFactory();
        }else if(userType.equalsIgnoreCase("General")){
            userFactory = new GeneralFactory();
        }else{
            System.out.println("Invalid user type give proper name");
            createUser();
        }
        User currentUser = userFactory.createUser();
        currentUser.register();

    }
    public static void createTrain(){

        // still havent added code where this function is called.
        System.out.println("Enter Name for Train :");
        String name = scanner.nextLine();
        System.out.println("Starting point of the train");
        String starting_point = scanner.nextLine();
        System.out.println("Destination of the train");
        String destination = scanner.nextLine();
        System.out.println("Now Adding compartments. Standard one is 2 General Compartment, 5 second class seater , " +
                "5 AC sleeper and 5 non-AC sleeper ");
        System.out.println("If u want to create a Standard train Enter Standard else type no and press enter");

        // add logic for  customizable train while expanding the application

        HashMap<Compartment_type,Integer> compartments = new HashMap<>();
        compartments.put(Compartment_type.GENERAL,1);
        compartments.put(Compartment_type.SLEEPER,5);
        compartments.put(Compartment_type.AC,5);
        compartments.put(Compartment_type.TwoS,5);
        compartments.put(Compartment_type.GENERAL,1);
        Train_Builder builder = new PassengerTrain_Builder();
        builder.setTrainName(name);
        builder.setStartingPoint(starting_point);
        builder.setDestination(destination);
        builder.setCompartments(compartments);
        Train current_train = builder.build();
        // go to addCompartments start the work for adding compartments into
        System.out.println("going to add compartments for the train name :"+name+" with id: "+current_train.getTrain_id());
        if(current_train.getTrain_id() != null){
            builder.addCompartments(current_train.getTrain_id());
        }else{
            System.err.println("train id is null");
        }
    }

    public static void addRoutes(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Adding routes to a train:");
        System.out.println("Enter TrainID :");
        String trainID = scanner.nextLine();
        int count = 1;
        List<trainStops> stopsList = new ArrayList<>();
        // use this method to add routes in code
        /*String[] stops = {"Chennai Central", "Tambaram", "Chengalpattu Junction", "Villupuram Junction",
                "Tiruppadirippuliyur", "Cuddalore Port Junction", "Chidambaram",
                "Mayiladuthurai Junction", "Kumbakonam", "Thanjavur Junction",
                "Tiruchirappalli Junction", "Dindigul Junction", "Madurai Junction",
                "Virudhunagar Junction", "Tirunelveli Junction"};


        for(String s : stops ){
            trainStops temp = new trainStops(s,count);
            stopsList.add(temp);
            count++;
        }*/
        while(count <= 20){
            System.out.println("Enter name of Stop "+count);
            String stopName = scanner.nextLine();
            if(stopName.equals("exit")){
                count--;
                System.out.println("stopping with only "+count+" stops");
                break;
            }
            trainStops temp = new trainStops(stopName,count);
            stopsList.add(temp);
            count++;
        }

        updateTrain updateTrain = new updateTrain();
        if(updateTrain.addRoute(trainID,stopsList)){
            System.out.println("Successfully added the stops to the Train with ID "+ trainID);
        }else{
            System.err.println("something wrong with adding the stops");
        }

    }

}