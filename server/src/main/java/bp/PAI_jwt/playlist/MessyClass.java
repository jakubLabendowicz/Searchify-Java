package bp.PAI_jwt.playlist;

import bp.PAI_jwt.model.Track;

import java.util.ArrayList;
import java.util.List;

/*
    Podział odpowiedzialności:
        Klasa Playlist zajmuje się tylko zarządzaniem listą utworów.
        Klasa PlaylistMetadata przechowuje dane meta, takie jak właściciel, twórca, data utworzenia i opis.
        Klasa PlaylistUtilities zawiera metody narzędziowe do operacji na playlistach, które nie powinny być częścią samej klasy Playlist.

    Zmniejszenie liczby argumentów:
        Konstruktor klasy Playlist i PlaylistMetadata mają teraz tylko te argumenty, które są im rzeczywiście potrzebne.

    Lepsze nazwy:
        Klasa PlaylistManager została podzielona i nazwy klas i metod zostały poprawione, aby lepiej odzwierciedlały ich funkcje.

    Usunięcie zbędnych komentarzy:
        Zbędne komentarze zostały usunięte, a kod jest bardziej czytelny i zrozumiały dzięki lepszym nazwom metod i zmiennych.
 */
public class MessyClass {
    private String playlistName;
    private List<Track> tracks;
    private String ownerName;
    private String createdBy;
    private long createdAt;
    private String description;
/*
    public PlaylistMetadata(String playlistName, String ownerName, String createdBy, long createdAt, String description) {
        this.playlistName = playlistName;
        this.ownerName = ownerName;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.description = description;
        this.tracks = new ArrayList<>();
    }
*/
    public String getPlaylistName() {
        return playlistName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public String getDescription() {
        return description;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    // Add a track to the playlist
    public void addTrackToPlaylist(Track track) {
        this.tracks.add(track);
    }

    // Remove a track from the playlist
    public void removeTrackFromPlaylist(Track track) {
        this.tracks.remove(track);
    }

    // Get the total duration of all tracks in the playlist
    public long getTotalDuration() {
        long totalDuration = 0;
        for (Track track : tracks) {
            totalDuration += Long.parseLong(track.getDurationMs());
        }
        return totalDuration;
    }

    // Print all track names
    public void printTrackNames() {
        for (Track track : tracks) {
            System.out.println(track.getName());
        }
    }

    // Find track by name
    public Track findTrackByName(String trackName) {
        for (Track track : tracks) {
            if (track.getName().equals(trackName)) {
                return track;
            }
        }
        return null;
    }
}
