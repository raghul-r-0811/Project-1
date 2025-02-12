package User;

public class AdminFactory implements UserFactory{
    @Override
    public User createUser() {
        return new AdminUser();
    }
}
