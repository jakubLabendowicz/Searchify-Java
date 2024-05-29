package bp.PAI_jwt.playlist;

 import bp.PAI_jwt.model.Track;
 import java.util.ArrayList;
 import java.util.List;

//Tydzień 7 Początek. Podejście przez Abstrakcję
//Tydzień 7 Początek. Podejście sterowania danymi
public class Playlist {
    private String name;
    private List<Track> tracks;

    public Playlist(String name) {
        this.name = name;
        this.tracks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Track> getTracks() {
        return tracks;
    }
    public void addTrack(Track track) {
        this.tracks.add(track);
    }

    public void removeTrack(Track track) {
        this.tracks.remove(track);
    }
    @Override
    public String toString() {
        return "Playlist{" +
                "name='" + name + '\'' +
                ", tracks=" + tracks +
                '}';
    }
}
//Tydzień 7 Koniec. Podejście przez Abstrakcję
//Tydzień 7 Koniec. Podejście sterowania danymi