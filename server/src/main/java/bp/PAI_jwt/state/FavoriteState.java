package bp.PAI_jwt.state;

import bp.PAI_jwt.model.Favorite;
import bp.PAI_jwt.repository.FavoriteRepository;
import bp.PAI_jwt.repository.TrackRepository;
import bp.PAI_jwt.repository.UserRepository;
import org.springframework.http.ResponseEntity;

// Interfejs stanu
//Tydzień 9, 5. Dostosuj funkcje tak, by przyjmowały maksymalnie 3 argumenty
interface FavoriteState {
    ResponseEntity<Favorite> createFavorite(FavoriteContext context) throws Exception;
}