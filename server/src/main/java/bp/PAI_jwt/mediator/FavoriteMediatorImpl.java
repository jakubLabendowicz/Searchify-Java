package bp.PAI_jwt.mediator;

import bp.PAI_jwt.model.Favorite;
import bp.PAI_jwt.model.Track;
import bp.PAI_jwt.model.User;
import bp.PAI_jwt.repository.FavoriteRepository;
import bp.PAI_jwt.repository.TrackRepository;
import bp.PAI_jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.Optional;

// Tydzień 5, Wzorzec Mediator
@Component
public class FavoriteMediatorImpl implements FavoriteMediator {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    public ResponseEntity<Favorite> addSelfFavorite(long userId, long trackId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Track> trackOptional = trackRepository.findById(trackId);

        if (userOptional.isPresent() && trackOptional.isPresent()) {
            User user = userOptional.get();
            Track track = trackOptional.get();

            if (favoriteAlreadyExists(user, track)) {
                return ResponseEntity.badRequest().build();
            }

            Favorite favorite = new Favorite(user, track);
            favoriteRepository.save(favorite);
            return ResponseEntity.status(HttpStatus.CREATED).body(favorite);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private boolean favoriteAlreadyExists(User user, Track track) {
        return favoriteRepository.findByUserAndTrack(user, track).isPresent();
    }
}
//Koniec, Tydzień 5, Wzorzec Mediator



