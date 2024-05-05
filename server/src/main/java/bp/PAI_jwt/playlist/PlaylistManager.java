package bp.PAI_jwt.playlist;

//Tydzień 7 Początek. Podejście sterowania danymi
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
//Tydzień 7 Koniec. Podejście sterowania danymi
