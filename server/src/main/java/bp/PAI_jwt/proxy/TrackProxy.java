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
        if (cachedTrack == null) {
            cachedTrack = fetchTrackById(trackId);
        }
        return buildBasicInfo(cachedTrack);
    }

    private Track fetchTrackById(long trackId) {
        try {
            return externalTrackService.getTrackById(trackId);
        } catch (Exception e) {
            //Tydzień 9, 6. Dodaj zwracanie wyjątków zamiast kodów błędów (3 wystąpienia)
            throw new RuntimeException("Failed to fetch track by id: " + trackId, e);
        }
    }

    private String buildBasicInfo(Track track) {
        return "Basic Info: " + track.getName() + " by " + track.getArtists();
    }
}
// Tydzień 4 Wzorzec Proxy Koniec