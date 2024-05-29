package bp.PAI_jwt.state;

import bp.PAI_jwt.model.Favorite;
import bp.PAI_jwt.repository.FavoriteRepository;
import bp.PAI_jwt.repository.FavoriteUserRepository;
import bp.PAI_jwt.repository.TrackRepository;
import bp.PAI_jwt.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class FavoriteContext {
    private FavoriteState currentState;
    private UserRepository userRepository;
    private FavoriteUserRepository favoriteRepository;
    private TrackRepository trackRepository;
    private long userId;
    private long trackId;

    public FavoriteContext() {
        this.currentState = new FavoriteDoesNotExistState(); // Domyślny stan: ulubiony utwór nie istnieje
    }

    public void setState(FavoriteState state) {
        this.currentState = state;
    }

    public void setContext(UserRepository userRepository, FavoriteUserRepository favoriteRepository, TrackRepository trackRepository, long userId, long trackId) {
        this.userRepository = userRepository;
        this.favoriteRepository = favoriteRepository;
        this.trackRepository = trackRepository;
        this.userId = userId;
        this.trackId = trackId;
    }

    public ResponseEntity<Favorite> createFavorite() throws Exception {
        return currentState.createFavorite(this);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public FavoriteUserRepository getFavoriteRepository() {
        return favoriteRepository;
    }

    public TrackRepository getTrackRepository() {
        return trackRepository;
    }

    public long getUserId() {
        return userId;
    }

    public long getTrackId() {
        return trackId;
    }
}