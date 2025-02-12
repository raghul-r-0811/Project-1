package User;

public class GenaralUser implements User{
    public GenaralUser() {
        System.out.println("GenaralUser created");
    }

    @Override
    public void giveUserType() {
        System.out.println("GenaralUser gives General User Type");
    }

    @Override
    public void register() {
        System.out.println("registering user");
        //get data and add it to the database
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
}
