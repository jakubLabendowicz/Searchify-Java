package bp.PAI_jwt.segregatedinterfaces;

import bp.PAI_jwt.model.User;

import java.util.List;

//Tydzień 8 Początek. Segregacja interfejsów
// Interfejsy dotyczące operacji specyficznych dla encji User, Favorite i Track
public interface UserRepository extends UserCrudRepository, UserOperations {
    User findByUsername(String username);
}
//Tydzień 8 Koniec. Segregacja interfejsów