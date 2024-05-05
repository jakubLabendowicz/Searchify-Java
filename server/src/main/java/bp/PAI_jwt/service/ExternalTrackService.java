package bp.PAI_jwt.service;

import bp.PAI_jwt.model.Track;
import org.springframework.stereotype.Service;

//Tydzień 9, 8. wyeliminuj magiczne liczby
@Service
public class ExternalTrackService {

    private static final String DEFAULT_TRACK_NAME = "Sample Track";
    private static final String DEFAULT_ARTIST_NAME = "Sample Artist";
    private static final String DEFAULT_SPOTIFY_ID = "sample_spotify_id";
    private static final String DEFAULT_PREVIEW_URL = "sample_preview_url";
    private static final String DEFAULT_POPULARITY = "sample_popularity";
    private static final String DEFAULT_DURATION_MS = "sample_duration_ms";
    private static final String DEFAULT_IMAGE_URL = "sample_image_url";

    // Symulacja zewnętrznej usługi do pobierania informacji o utworach
    public Track getTrackById(long id) {
        // Tutaj byłoby wywołanie rzeczywistej usługi zewnętrznej,
        // aby pobrać informacje o utworze o podanym identyfikatorze.
        // W tej wersji demonstracyjnej, zwracamy prosty obiekt Track.

        // Załóżmy, że istnieje obiekt Track z danymi utworu o podanym id
        return createSampleTrack();
    }

    private Track createSampleTrack() {
        String name = DEFAULT_TRACK_NAME;
        String artist = DEFAULT_ARTIST_NAME;
        String spotifyId = DEFAULT_SPOTIFY_ID;
        String previewUrl = DEFAULT_PREVIEW_URL;
        String popularity = DEFAULT_POPULARITY;
        String durationMs = DEFAULT_DURATION_MS;
        String image = DEFAULT_IMAGE_URL;

        return new Track(name, artist, spotifyId, previewUrl, popularity, durationMs, image);
    }
}
