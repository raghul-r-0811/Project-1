package User;

import java.util.Scanner;

import DAO.User.readUser;
import DAO.User.regUserDAO;

public class GenaralUser implements User{
    private String gUserName;
    private String gUserid;
    private String gUserEmail;
    private String gUserPassword;

    Scanner scanner = new Scanner(System.in);
    //public GenaralUser() {
        //System.out.println("GenaralUser created");
    //}

    @Override
    public void giveUserType() {
        System.out.println("GenaralUser gives General User Type");
    }

    @Override
    public void register() {
        System.out.println("registering general user");
        //get data and add it to the database
        System.out.println("enter user name :");
        setgUserName(scanner.nextLine());
        System.out.println("enter your emailId");
        setgUserEmail(scanner.nextLine());
        //try to hash it. now storing without hashing
        System.out.println("enter your password");
        setgUserPassword(scanner.nextLine());
        regUserDAO daoObj = regUserDAO.getInstance();
        daoObj.register(this);

    }

    @Override
    public User login() {
        boolean success = false;
        readUser readUser = new readUser();
         while(!success){
             System.out.println("Enter you userID");
             String id = scanner.nextLine();
             System.out.println("Enter your password");
             String password = scanner.nextLine();
             success = readUser.checkIsGeneralUserTrue(id,password,this);
             if(!success){
                 System.out.println("Wrong userID or password please enter valid credentials(I can just say valid data, but just trying to be cool)");
             }
         }
        System.out.println("Login Successfull");
        System.out.println("Welcome "+ this.gUserName);
        return this;

    }

    @Override
    public void viewProfile() {

    }

    @Override
    public void editProfile() {

    }

    public String getgUserName() {
        return gUserName;
    }

    public void setgUserName(String gUserName) {
        this.gUserName = gUserName;
    }

    public String getgUserid() {
        return gUserid;
    }

    public void setgUserid(String gUserid) {
        this.gUserid = gUserid;
    }

    public String getgUserEmail() {
        return gUserEmail;
    }

    public void setgUserEmail(String gUserEmail) {
        this.gUserEmail = gUserEmail;
    }

    public String getgUserPassword() {
        return gUserPassword;
    }

    public void setgUserPassword(String gUserPassword) {
        this.gUserPassword = gUserPassword;
    }

    //@Override
//    protected void finalize() throws Throwable {
//        super.finalize();
//    }
}
