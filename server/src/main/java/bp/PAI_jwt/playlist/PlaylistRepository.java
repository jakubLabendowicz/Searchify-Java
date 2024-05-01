package bp.PAI_jwt.playlist;

//Podejście "Sterowanie Danymi"

import java.util.HashMap;
import java.util.Map;


//Tydzień 7 Początek. Podejście sterowania danymi
public class PlaylistRepository {
    private final Map<String, Playlist> database = new HashMap<>();

    public void savePlaylist(Playlist playlist) {
        database.put(playlist.getName(), playlist);
        System.out.println("Playlist saved: " + playlist.getName());
    }

    public void deletePlaylist(Playlist playlist) {
        if (database.containsKey(playlist.getName())) {
            database.remove(playlist.getName());
            System.out.println("Playlist deleted: " + playlist.getName());
        } else {
            System.out.println("Playlist not found: " + playlist.getName());
        }
    }

    public Playlist findPlaylistByName(String name) {
        return database.get(name);
    }
}
//Tydzień 7 Kopniec. Podejście sterowania danymi
