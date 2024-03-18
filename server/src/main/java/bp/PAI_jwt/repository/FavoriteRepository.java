package bp.PAI_jwt.repository;

import bp.PAI_jwt.model.Favorite;
import bp.PAI_jwt.model.User;
import bp.PAI_jwt.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findByUser(User user);

    List<Favorite> findByTrack(Track track);

    Optional<Favorite> findByUserAndTrack(User user, Track track);
}
