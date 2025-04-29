package TrainPackage.Train.Ticket;

import DAO.Train.TicketDAO;
import User.GenaralUser;

import java.lang.invoke.StringConcatFactory;
import java.util.Map;
import java.util.Scanner;

public class TrainTicket {
    String userName;
    String userId;
    String from;
    String to;
    String ticket_No;
    String trainID;
    String trainName;
    int seatNo;
    String coachType;

    public void bookTicket(GenaralUser user){
        this.userId = user.getgUserid();
        this.userName = user.getgUserName();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter TrainID ");
        this.trainID = scanner.nextLine();
        System.out.println("press \n1 for General \n 2 for second Class \n " +
                "3 for sleeper \n 4 for AC sleeper ");
        int type = scanner.nextInt();
        while (true){
            switch (type){
                case 1 : this.coachType = "General"; break;
                case 2 : this.coachType = "TwoSeater"; break;
                case 3 : this.coachType = "Sleeper"; break;
                case 4 : this.coachType = "AC"; break;
                default:
                    System.out.println("wrong type please enter correct option");continue;

            }
            break;
        }
        TicketDAO ticketDAO = new TicketDAO();
        boolean ticketBooked =ticketDAO.bookTicket(this);
    }

    public void checkTrainAvalibility(GenaralUser user){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter starting point");
        this.from = scanner.nextLine();
        System.out.println("Enter Destination");
        this.to = scanner.nextLine();
        TicketDAO ticketDAO = new TicketDAO();
        Map<String,String> trainMap = ticketDAO.checkTrain_Availability(this.from,this.to);
        if(trainMap.size() == 0){
            System.out.println("No train is available between these stations");
        }else{
            String yellow = "\u001B[33m";
            String reset = "\u001B[0m";
            String blue = "\u001B[34m";
            System.out.println(yellow+ "Available trains : "+ reset);
            for(Map.Entry<String,String> entry : trainMap.entrySet()){
                System.out.println(blue + entry.getKey() +" - "+entry.getValue()+reset);
            }
        }
        System.out.println("If you want to book ticket press 1 if not press 2");
        int choice = scanner.nextInt();
        if(choice == 1){
            bookTicket(user);
        }
    }

}
