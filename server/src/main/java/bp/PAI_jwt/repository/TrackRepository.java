package bp.PAI_jwt.repository;

import java.util.List;

import bp.PAI_jwt.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;

// Tydzień 7 Początek. Zastosowanie zasady pojedynczej odpowiedzialnosci w klasie Track
public interface TrackRepository extends JpaRepository<Track, Long> {
    List<Track> findByNameContaining(String name);
}
// Tydzień 7 Koniec. Zastosowanie zasady pojedynczej odpowiedzialnosci w klasie Track