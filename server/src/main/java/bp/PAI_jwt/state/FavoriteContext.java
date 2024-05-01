package bp.PAI_jwt.state;

import bp.PAI_jwt.model.Favorite;
import bp.PAI_jwt.repository.FavoriteRepository;
import bp.PAI_jwt.repository.TrackRepository;
import bp.PAI_jwt.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class FavoriteContext {
    private FavoriteState currentState;

    public FavoriteContext() {
        this.currentState = new FavoriteDoesNotExistState(); // Domyślny stan: ulubiony utwór nie istnieje
    }

    public void setState(FavoriteState state) {
        this.currentState = state;
    }

    public ResponseEntity<Favorite> createFavorite(UserRepository userRepository, FavoriteRepository favoriteRepository, TrackRepository trackRepository, long userId, long trackId) {
        return currentState.createFavorite(userRepository, favoriteRepository, trackRepository, userId, trackId);
    }
}