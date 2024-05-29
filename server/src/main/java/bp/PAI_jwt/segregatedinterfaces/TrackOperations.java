package bp.PAI_jwt.segregatedinterfaces;

import bp.PAI_jwt.model.Track;

import java.util.List;

public interface TrackOperations {
    List<Track> findByNameContaining(String name);
}