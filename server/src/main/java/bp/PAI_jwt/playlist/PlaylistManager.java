package bp.PAI_jwt.playlist;

//Podejście "Sterowanie Danymi"
public class PlaylistManager {
    private final PlaylistRepository playlistRepository;

    public PlaylistManager(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public void savePlaylist(Playlist playlist) {
        playlistRepository.savePlaylist(playlist);
    }

    public void deletePlaylist(Playlist playlist) {
        playlistRepository.deletePlaylist(playlist);
    }

    // Inne metody zarządzania playlistami
}
