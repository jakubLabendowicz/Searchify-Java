package bp.PAI_jwt.segregatedinterfaces;

import bp.PAI_jwt.model.Favorite;
import bp.PAI_jwt.model.Track;
import bp.PAI_jwt.model.User;

import java.util.List;
import java.util.Optional;
//Tydzień 8 Początek. Segregacja interfejsów
public interface FavoriteRepository extends FavoriteCrudRepository {
    List<Favorite> findByUser(User user);

    List<Favorite> findByTrack(Track track);

    Optional<Favorite> findByUserAndTrack(User user, Track track);
}
//Tydzień 8 Koniec. Segregacja interfejsów
