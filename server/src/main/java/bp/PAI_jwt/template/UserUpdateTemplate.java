package bp.PAI_jwt.template;

import bp.PAI_jwt.model.User;
import bp.PAI_jwt.repository.UserRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

// Tydzień 5 Wzorzec Template Początek
//UserUpdateTemplate jest klasą implementującą szczegóły operacji w ramach szablonu.
// W tym przypadku wykonuje operację aktualizacji danych użytkownika.
public class UserUpdateTemplate extends UserTemplate {

    private final UserRepository userRepository;

    public UserUpdateTemplate(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected Optional<User> getUserById(long userId) {
        return userRepository.findById(userId);
    }

    @Override
    protected ResponseEntity<String> performOperation(User user) {
        // Pobierz istniejące imię użytkownika
        String existingFirstName = user.getFirstName();

        // Zaktualizuj imię użytkownika
        //user.setFirstName("NoweImie");
        user.setFirstname("Nowe Imie");
        // Zapisz zaktualizowanego użytkownika do bazy danych
        userRepository.save(user);

        // Zwróć odpowiedź ResponseEntity informującą o zaktualizowanym imieniu użytkownika
        return ResponseEntity.ok("Imię użytkownika " + user.getId() + " zostało zaktualizowane z " + existingFirstName + " na " + user.getFirstName());
    }
}
// Tydzień 5 Wzorzec Template Koniec