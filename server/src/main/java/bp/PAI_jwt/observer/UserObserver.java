package bp.PAI_jwt.observer;

import bp.PAI_jwt.model.User;

// Tydzień 6, Wzorzec Observer
// Interfejs `UserObserver` definiuje metodę `onUserLogged`, która jest wywoływana, gdy użytkownik loguje się.
// Kod implementuje wzorzec projektowy "Observer", który umożliwia obserwatorom subskrybowanie i otrzymywanie powiadomień o zmianach stanu obiektu.
public interface UserObserver {
    void onUserLogged(User user);
}
//Koniec, Tydzień 6, Wzorzec Observer
