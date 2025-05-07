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
    String compartmentName;
    String coachType;



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTicket_No() {
        return ticket_No;
    }

    public void setTicket_No(String ticket_No) {
        this.ticket_No = ticket_No;
    }

    public String getTrainID() {
        return trainID;
    }

    public void setTrainID(String trainID) {
        this.trainID = trainID;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public String getCompartmentName() {
        return compartmentName;
    }

    public void setCompartmentName(String compartmentName) {
        this.compartmentName = compartmentName;
    }

    public String getCoachType() {
        return coachType;
    }

    public void setCoachType(String coachType) {
        this.coachType = coachType;
    }



    @Override
    public String toString() {
        return "TrainTicket{" +
                "ticket_No='" + ticket_No + '\'' +
                "userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +

                ", trainID='" + trainID + '\'' +
                ", trainName='" + trainName + '\'' +
                ", seatNo=" + seatNo +
                ", compartmentName='" + compartmentName + '\'' +
                ", coachType='" + coachType + '\'' +
                '}';
    }

    public void bookTicket(GenaralUser user){
        //System.out.println("booking successfull");

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
        if(ticketBooked){
            System.out.println("Ticket Booked Successfully");
            String printTicket = toString();
            System.out.println(printTicket);
        }

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
