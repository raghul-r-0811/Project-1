package MainPack;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import User.*;

import javax.security.auth.login.CredentialException;

public class Operations {

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
        }catch(NoSuchElementException e){
            System.out.println("No input availabele");
        }catch(IllegalStateException e){
            System.out.println("Scanner is closed");
        }
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


}
