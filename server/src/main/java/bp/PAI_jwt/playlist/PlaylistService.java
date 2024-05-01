package bp.PAI_jwt.playlist;

import bp.PAI_jwt.model.Track;

//Podejście przez Abstrakcję
public class PlaylistService implements PlaylistOperation {
    @Override
    public void addTrack(Playlist playlist, Track track) {
        playlist.addTrack(track);
    }

    @Override
    public void removeTrack(Playlist playlist, Track track) {
        playlist.removeTrack(track);
    }

    // Inne metody biznesowe związane z playlistami
}
