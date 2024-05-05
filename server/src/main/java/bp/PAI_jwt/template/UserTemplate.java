package bp.PAI_jwt.template;

import bp.PAI_jwt.model.User;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

// Tydzień 5 Wzorzec Template Początek
// UserTemplate definiuje ogólny szkielet algorytmu operacji na użytkownikach
public abstract class UserTemplate {

    protected abstract Optional<User> getUserById(long userId);

    protected abstract ResponseEntity<String> performOperation(User user);

    public ResponseEntity<String> executeOperation(long userId) {
        Optional<User> userOptional = getUserById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return performOperation(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
// Tydzień 5 Wzorzec Template Koniec