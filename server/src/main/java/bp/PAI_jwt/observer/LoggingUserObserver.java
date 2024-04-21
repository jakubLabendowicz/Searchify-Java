package bp.PAI_jwt.observer;

import bp.PAI_jwt.model.User;

// Tydzień 6, Wzorzec Observer
// Klasa `LoggingUserObserver` implementuje interfejs `UserObserver`, definiując metodę `onUserLogged`, która wypisuje nazwę użytkownika na konsoli po zalogowaniu.
// Kod ten stanowi konkretną implementację obserwatora, który reaguje na zdarzenie logowania użytkownika.
public class LoggingUserObserver implements UserObserver {
    @Override
    public void onUserLogged(User user) {
        System.out.println("User logged: " + user.getUsername());
    }
}
//Koniec, Tydzień 6, Wzorzec Observer
