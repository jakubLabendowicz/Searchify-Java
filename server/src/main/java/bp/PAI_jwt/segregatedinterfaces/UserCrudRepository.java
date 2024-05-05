package bp.PAI_jwt.segregatedinterfaces;

import bp.PAI_jwt.model.User;

//Tydzień 8 Początek. Segregacja interfejsów
// Interfejsy dotyczące operacji CRUD na encjach User, Favorite i Track
public interface UserCrudRepository {
    void save(User user);

    User findById(Long id);

    void deleteById(Long id);
}

//Tydzień 8 Początek. Segregacja interfejsów
