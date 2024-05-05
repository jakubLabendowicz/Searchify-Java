package bp.PAI_jwt.segregatedinterfaces;

import bp.PAI_jwt.model.Track;

import java.util.List;

//Tydzień 8 Początek. Segregacja interfejsów
public interface TrackRepository extends TrackCrudRepository {
    List<Track> findByNameContaining(String name);
}
//Tydzień 8 Koniec. Segregacja interfejsów
