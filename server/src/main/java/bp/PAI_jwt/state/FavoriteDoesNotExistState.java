package bp.PAI_jwt.state;

import bp.PAI_jwt.model.Favorite;
import bp.PAI_jwt.model.Track;
import bp.PAI_jwt.model.User;
import bp.PAI_jwt.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

// Stan, gdy ulubiony utwór nie istnieje
class FavoriteDoesNotExistState implements FavoriteState {
    @Override
    public ResponseEntity<Favorite> createFavorite(FavoriteContext context) throws Exception {
        UserRepository userRepository = context.getUserRepository();
        FavoriteUserRepository favoriteRepository = context.getFavoriteRepository();
        FavoriteUserTrackRepository favoriteUserTrackRepository = (FavoriteUserTrackRepository) favoriteRepository;
        TrackRepository trackRepository = context.getTrackRepository();
        long userId = context.getUserId();
        long trackId = context.getTrackId();

        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Track> trackOptional = trackRepository.findById(trackId);

        if (userOptional.isPresent() && trackOptional.isPresent()) {
            User user = userOptional.get();
            Track track = trackOptional.get();

            Optional<Favorite> existingFavorite = favoriteUserTrackRepository.findByUserAndTrack(user, track);

            if (existingFavorite.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Favorite already exists
            }

            Favorite favorite = new Favorite(user, track);
            favoriteRepository.save(favorite);
            return ResponseEntity.status(HttpStatus.CREATED).body(favorite);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // User or Track not found
        }
    }
}