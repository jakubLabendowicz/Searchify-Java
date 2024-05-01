package bp.PAI_jwt.playlist;

import bp.PAI_jwt.model.Track;

//Podejście przez Abstrakcję
public interface PlaylistOperation {
    void addTrack(Playlist playlist, Track track);
    void removeTrack(Playlist playlist, Track track);
}
