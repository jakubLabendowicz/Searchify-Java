package bp.PAI_jwt.proxy;

import bp.PAI_jwt.bridge.TrackOperations;
import bp.PAI_jwt.model.Track;
import bp.PAI_jwt.service.ExternalTrackService;
//// Tydzień 4 Wzorzec Proxy Początek
public class TrackProxy implements TrackOperations {
    private final ExternalTrackService externalTrackService;
    private final long trackId;
    private Track cachedTrack;

    public TrackProxy(ExternalTrackService externalTrackService, long trackId) {
        this.externalTrackService = externalTrackService;
        this.trackId = trackId;
    }

    @Override
    public String getBasicInfo() {
        // Pobierz informacje o utworze tylko wtedy, gdy są one potrzebne i nie są jeszcze zcache'owane
        if (cachedTrack == null) {
            cachedTrack = externalTrackService.getTrackById(trackId);
        }

        // Zwróć podstawowe informacje o utworze
        return "Basic Info: " + cachedTrack.getName() + " by " + cachedTrack.getArtists();
    }
}
// Tydzień 4 Wzorzec Proxy Koniec