package bp.PAI_jwt.segregatedinterfaces;

import bp.PAI_jwt.model.Favorite;
//Tydzień 8 Początek. Segregacja interfejsów
public interface FavoriteCrudRepository {
    void save(Favorite favorite);

    Favorite findById(Long id);

    void deleteById(Long id);
}

//Tydzień 8 Konieck. Segregacja interfejsów