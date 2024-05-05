package bp.PAI_jwt.template;

import bp.PAI_jwt.model.User;
import bp.PAI_jwt.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

// Tydzień 5 Wzorzec Template Początek
// UserUpdateTemplate jest klasą implementującą szczegóły operacji w ramach szablonu.
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
        String existingFirstName = user.getFirstname();
        updateFirstName(user);
        userRepository.save(user);
        return ResponseEntity.ok("Imię użytkownika " + user.getId() + " zostało zaktualizowane z " + existingFirstName + " na " + user.getFirstname());
    }

    private void updateFirstName(User user) {
        // Aktualizacja imienia użytkownika
        user.setFirstname("Nowe Imie");
    }
}
// Tydzień 5 Wzorzec Template Koniec