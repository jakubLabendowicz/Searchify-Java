package bp.PAI_jwt.command;

import bp.PAI_jwt.bridge.TrackOperations;
import bp.PAI_jwt.proxy.TrackProxy;
import bp.PAI_jwt.service.ExternalTrackService;

// Tydzień 4 Wzorzec Command Początek
// Concrete command class for retrieving basic track info
public class BasicTrackInfoCommand implements TrackCommand {
    private final ExternalTrackService externalTrackService;
    private final long trackId;

    public BasicTrackInfoCommand(ExternalTrackService externalTrackService, long trackId) {
        this.externalTrackService = externalTrackService;
        this.trackId = trackId;
    }

    @Override
    public String execute() {
        TrackOperations trackProxy = new TrackProxy(externalTrackService, trackId);
        return trackProxy.getBasicInfo();
    }
}
// Tydzień 4 Wzorzec Command Koniec