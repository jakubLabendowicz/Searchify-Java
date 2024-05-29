package bp.PAI_jwt.segregatedinterfaces;

import bp.PAI_jwt.model.Track;

import java.util.List;

public interface TrackCrudRepository extends CrudRepository<Track> {
    void save(Track track);

    Track findById(Long id);

    void deleteById(Long id);

    long count();

    List<Track> findByArtist(String artist);
}

