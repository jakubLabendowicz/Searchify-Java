package bp.PAI_jwt.model;

import bp.PAI_jwt.prototype.Cloneable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tracks")
public class Track implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "artists")
    private String artists;

    @Column(name = "spotifyId")
    private String spotifyId;

    @Column(name = "previewUrl")
    private String previewUrl;

    @Column(name = "popularity")
    private String popularity;

    @Column(name = "durationMs")
    private String durationMs;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "track", cascade = CascadeType.ALL)
    private List<Favorite> favorites;

    public Track() {
    }

    public Track(String name, String artists, String spotifyId, String previewUrl, String popularity, String durationMs, String image) {
        this.name = name;
        this.artists = artists;
        this.spotifyId = spotifyId;
        this.previewUrl = previewUrl;
        this.popularity = popularity;
        this.durationMs = durationMs;
        this.image = image;
    }

    public Track(long id, String name, String artists, String spotifyId, String previewUrl, String popularity, String durationMs, String image) {
        this.id = id;
        this.name = name;
        this.artists = artists;
        this.spotifyId = spotifyId;
        this.previewUrl = previewUrl;
        this.popularity = popularity;
        this.durationMs = durationMs;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtists() {
        return artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    public String getSpotifyId() {
        return spotifyId;
    }

    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(String durationMs) {
        this.durationMs = durationMs;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artists='" + artists + '\'' +
                ", spotifyId='" + spotifyId + '\'' +
                ", previewUrl='" + previewUrl + '\'' +
                ", popularity='" + popularity + '\'' +
                ", durationMs='" + durationMs + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    //Tydzień 1, Wzorzec Prototype, ......<dwulinijkowy opis>
    @Override
    public Object clone() {
        return new Track(id, name, artists, spotifyId, previewUrl, popularity, durationMs, image);
    }
    //Koniec, Tydzień 1, Wzorzec Prototype

}
