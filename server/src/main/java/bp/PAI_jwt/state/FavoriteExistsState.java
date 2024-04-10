package bp.PAI_jwt.state;

import bp.PAI_jwt.model.Favorite;
import bp.PAI_jwt.repository.FavoriteRepository;
import bp.PAI_jwt.repository.TrackRepository;
import bp.PAI_jwt.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// Stan, gdy ulubiony utwór już istnieje
class FavoriteExistsState implements FavoriteState {
    @Override
    public ResponseEntity<Favorite> createFavorite(UserRepository userRepository, FavoriteRepository favoriteRepository, TrackRepository trackRepository, long userId, long trackId) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Favorite already exists
    }
}