package bp.PAI_jwt.flyweight;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import bp.PAI_jwt.model.User;
import bp.PAI_jwt.repository.UserRepository;

// Tydzień 4, Wzorzec Flyweight
// Klasa `UserFlyweight` implementuje wzorzec Flyweight, który umożliwia współdzielenie obiektów w celu zaoszczędzenia pamięci poprzez przechowywanie użytkowników w statycznej mapie,
// gdzie kluczem jest nazwa użytkownika, aby uniknąć powtarzających się obiektów User i zmniejszyć liczbę operacji pobierania z repozytorium poprzez cachowanie wyników.
public class UserFlyweight {
    private static final Map<String, Optional<User>> users = new HashMap<>();

    public static Optional<User> findByUsername(String username, UserRepository userRepository) {
        if (users.containsKey(username)) {
            System.out.println("User found in cache: " + username);
            return users.get(username);
        } else {
            System.out.println("User not found in cache: " + username);
            Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
            users.put(username, user);
            return user;
        }
    }
}
//Koniec, Tydzień 4, Wzorzec Flyweight
