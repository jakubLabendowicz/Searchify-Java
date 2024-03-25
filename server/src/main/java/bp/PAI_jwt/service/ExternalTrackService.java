package bp.PAI_jwt.service;

import bp.PAI_jwt.model.Track;
import org.springframework.stereotype.Service;

@Service
public class ExternalTrackService {

    // Symulacja zewnętrznej usługi do pobierania informacji o utworach
    public Track getTrackById(long id) {
        // Tutaj byłoby wywołanie rzeczywistej usługi zewnętrznej,
        // aby pobrać informacje o utworze o podanym identyfikatorze.
        // W tej wersji demonstracyjnej, zwracamy prosty obiekt Track.

        // Załóżmy, że istnieje obiekt Track z danymi utworu o podanym id
        String name = "Sample Track";
        String artist = "Sample Artist";
        String spotifyId = "sample_spotify_id";
        String previewUrl = "sample_preview_url";
        String popularity = "sample_popularity";
        String durationMs = "sample_duration_ms";
        String image = "sample_image_url";

        return new Track(name, artist, spotifyId, previewUrl, popularity, durationMs, image);
    }
}
