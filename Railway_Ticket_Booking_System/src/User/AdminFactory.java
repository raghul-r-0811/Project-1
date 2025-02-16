package User;

public class AdminFactory implements UserFactory{
    @Override
    public User createUser() {
        return new AdminUser();
        //System.out.println("adminfactoy.createUser");
       // AdminUser temp = new AdminUser();
//        if(temp == null){
//            System.out.println("null");
//        }
//        return temp;
    }

}
