package bp.PAI_jwt.segregatedinterfaces;

import bp.PAI_jwt.model.Track;

import java.util.List;

//Tydzień 8 Początek. Segregacja interfejsów
public interface TrackRepository extends TrackCrudRepository, TrackOperations {
    long count();

    List<Track> findByNameContaining(String name);

    List<Track> findByArtist(String artist);
}
//Tydzień 8 Koniec. Segregacja interfejsów
