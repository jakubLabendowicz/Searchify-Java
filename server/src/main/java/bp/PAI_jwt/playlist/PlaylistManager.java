package bp.PAI_jwt.playlist;

import bp.PAI_jwt.model.Track;

public class PlaylistManager {
    public void printPlaylistDetails(Playlist playlist) {
        System.out.println(playlist);
    }

    public void addTrackToPlaylist(Playlist playlist, Track track) {
        playlist.addTrack(track);
    }
}