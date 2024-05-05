package bp.PAI_jwt.playlist;

import bp.PAI_jwt.model.Track;

//Tydzień 7 Początek. Podejście przez Abstrakcję
public interface PlaylistOperation {
    void addTrack(Playlist playlist, Track track);
    void removeTrack(Playlist playlist, Track track);
}
//Tydzień 7 Koniec. Podejście przez Abstrakcję
