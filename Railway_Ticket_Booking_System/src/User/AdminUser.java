package User;

import DAO.adminUserDAO;

import java.util.Scanner;

public class AdminUser implements User {


    String adminID;
    String adminName;
    String adminPassword;
    String adminEmail;

    Scanner scanner = new Scanner(System.in);
    public AdminUser(){
        System.out.println("Admin User created");
    }

    @Override
    public void giveUserType() {
        System.out.println("Admin User gives Adminuser Type");
    }
    // create train
    public void createTrain(){

    }

    @Override
    public void login() {

    }

    @Override
    public void viewProfile() {

    }

    @Override
    public void register() {
        System.out.println("enter admin Name :");
        setAdminName(scanner.nextLine());
        //scanner.nextLine();
        System.out.println("enter email id :");
        setAdminEmail(scanner.nextLine());
        System.out.println("enter password");
        setAdminPassword(scanner.nextLine());
        adminUserDAO dao = new adminUserDAO();
        dao.register(this);
    }

    public void modifyTrainDetails(){

    }

    @Override
    public void editProfile() {

    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }
}
