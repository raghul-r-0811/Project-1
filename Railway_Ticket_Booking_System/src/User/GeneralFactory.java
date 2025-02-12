package User;

public class GeneralFactory implements UserFactory {
    @Override
    public User createUser() {
        return new GenaralUser();
    }
}
