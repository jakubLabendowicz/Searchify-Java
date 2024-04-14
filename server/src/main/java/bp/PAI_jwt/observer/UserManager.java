package bp.PAI_jwt.observer;

import bp.PAI_jwt.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<UserObserver> observers = new ArrayList<>();

    public void addObserver(UserObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(UserObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(User user) {
        for (UserObserver observer : observers) {
            observer.onUserLogged(user);
        }
    }
}
