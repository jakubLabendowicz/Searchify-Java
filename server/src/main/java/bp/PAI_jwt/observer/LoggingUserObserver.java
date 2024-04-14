package bp.PAI_jwt.observer;

import bp.PAI_jwt.model.User;

public class LoggingUserObserver implements UserObserver {
    @Override
    public void onUserLogged(User user) {
        // Log the user update
        System.out.println("User logged: " + user.getUsername());
    }
}
