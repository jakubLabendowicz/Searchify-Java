package bp.PAI_jwt.state;

import bp.PAI_jwt.model.Favorite;
import bp.PAI_jwt.model.Track;
import bp.PAI_jwt.model.User;
import bp.PAI_jwt.repository.FavoriteRepository;
import bp.PAI_jwt.repository.TrackRepository;
import bp.PAI_jwt.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

// Stan, gdy ulubiony utwór nie istnieje
class FavoriteDoesNotExistState implements FavoriteState {
    @Override
    public ResponseEntity<Favorite> createFavorite(UserRepository userRepository, FavoriteRepository favoriteRepository, TrackRepository trackRepository, long userId, long trackId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Track> trackOptional = trackRepository.findById(trackId);

        if (userOptional.isPresent() && trackOptional.isPresent()) {
            User user = userOptional.get();
            Track track = trackOptional.get();

            Optional<Favorite> existingFavorite = favoriteRepository.findByUserAndTrack(user, track);

            if (existingFavorite.isPresent()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Favorite already exists
            }

            Favorite favorite = new Favorite(user, track);
            favoriteRepository.save(favorite);
            return new ResponseEntity<>(favorite, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // User or Track not found
        }
    }
}