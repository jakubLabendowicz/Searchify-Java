package bp.PAI_jwt.builder;

import bp.PAI_jwt.model.Track;

import java.util.ArrayList;
import java.util.List;

//Tydzień 1, Wzorzec Builder,
// Klasa TrackBuilder służy do budowania obiektów Track.
// Umożliwia ustawianie poszczególnych pól tracka, takich jak nazwa, artysta, identyfikator Spotify, itd.
public class TrackBuilder {

    private String name;
    private List<String> artists;
    private String spotifyId;
    private String previewUrl;
    private String popularity;
    private String durationMs;
    private String image;

    public TrackBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public TrackBuilder setArtist(String artist) {
        if (artists == null) {
            artists = new ArrayList<>();
        }
        artists.add(artist);
        return this;
    }

    public TrackBuilder setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
        return this;
    }

    public TrackBuilder setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
        return this;
    }

    public TrackBuilder setPopularity(String popularity) {
        this.popularity = popularity;
        return this;
    }

    public TrackBuilder setDurationMs(String durationMs) {
        this.durationMs = durationMs;
        return this;
    }

    public TrackBuilder setImage(String image) {
        this.image = image;
        return this;
    }

    public Track build() {

        return new Track(name, String.join(",", artists), spotifyId, previewUrl, popularity, durationMs, image);
    }
}
//Koniec, Tydzień 1, Wzorzec Builder
