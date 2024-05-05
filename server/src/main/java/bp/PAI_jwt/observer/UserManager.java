package bp.PAI_jwt.observer;

import bp.PAI_jwt.model.User;

import java.util.ArrayList;
import java.util.List;

// Tydzień 6, Wzorzec Observer
// Klasa `UserManager` zarządza listą obserwatorów `UserObserver`, umożliwiając dodawanie, usuwanie oraz powiadamianie ich o zdarzeniach związanych z logowaniem użytkownika.
// Kod implementuje wzorzec projektowy "Observer", gdzie obiekty obserwatorów subskrybują zmiany w obiekcie `UserManager` i otrzymują powiadomienia w przypadku wystąpienia zdarzeń.
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
//Koniec, Tydzień 6, Wzorzec Observer