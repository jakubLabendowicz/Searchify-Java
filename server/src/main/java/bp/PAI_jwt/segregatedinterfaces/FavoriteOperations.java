package bp.PAI_jwt.segregatedinterfaces;

import bp.PAI_jwt.model.Favorite;
import bp.PAI_jwt.model.Track;
import bp.PAI_jwt.model.User;

import java.util.List;
import java.util.Optional;

public interface FavoriteOperations {
    List<Favorite> findByUser(User user);
    List<Favorite> findByTrack(Track track);
    Optional<Favorite> findByUserAndTrack(User user, Track track);
}