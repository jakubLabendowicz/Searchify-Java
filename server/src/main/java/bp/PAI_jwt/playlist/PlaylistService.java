package bp.PAI_jwt.playlist;

import bp.PAI_jwt.model.Track;

//Tydzień 7 Początek. Podejście przez Abstrakcję
public class PlaylistService implements PlaylistOperation {
    @Override
    public void addTrack(Playlist playlist, Track track) {
        playlist.addTrack(track);
    }

    @Override
    public void removeTrack(Playlist playlist, Track track) {
        playlist.removeTrack(track);
    }

    // Inne metody biznesowe związane z playlistami...
}
//Tydzień 7 Koniec. Podejście przez Abstrakcję