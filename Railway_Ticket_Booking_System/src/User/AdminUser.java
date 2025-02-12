package User;

public class AdminUser implements User {

    String adminID;
    String adminName;
    String adminPassword;
    String adminEmail;
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
    public void editProfile() {

    }

    @Override
    public void register() {

    }

    public void modifyTrainDetails(){

    }


}
