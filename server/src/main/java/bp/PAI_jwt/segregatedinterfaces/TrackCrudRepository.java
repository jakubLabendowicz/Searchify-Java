package bp.PAI_jwt.segregatedinterfaces;

import bp.PAI_jwt.model.Track;

//Tydzień 8 Początek. Segregacja interfejsów
public interface TrackCrudRepository {
    void save(Track track);

    Track findById(Long id);

    void deleteById(Long id);
}

//Tydzień 8 Koniec. Segregacja interfejsów
