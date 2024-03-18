package bp.PAI_jwt.repository;

import java.util.List;

import bp.PAI_jwt.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Long> {
    List<Track> findByNameContaining(String name);
}
