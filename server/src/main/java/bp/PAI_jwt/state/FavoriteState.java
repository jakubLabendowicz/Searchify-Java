package bp.PAI_jwt.state;

import bp.PAI_jwt.model.Favorite;
import bp.PAI_jwt.repository.FavoriteRepository;
import bp.PAI_jwt.repository.TrackRepository;
import bp.PAI_jwt.repository.UserRepository;
import org.springframework.http.ResponseEntity;

// Interfejs stanu
interface FavoriteState {
    ResponseEntity<Favorite> createFavorite(UserRepository userRepository, FavoriteRepository favoriteRepository, TrackRepository trackRepository, long userId, long trackId);
}