package bp.PAI_jwt.segregatedinterfaces;

import bp.PAI_jwt.model.User;

//Tydzień 8 Początek. Segregacja interfejsów
// Interfejsy dotyczące operacji specyficznych dla encji User, Favorite i Track
public interface UserRepository extends UserCrudRepository {
    User findByUsername(String username);
}
//Tydzień 8 Koniec. Segregacja interfejsów
