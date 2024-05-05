package bp.PAI_jwt.bridge;

import bp.PAI_jwt.model.Track;

//Tydzień 2. Wzorzec Bridge Początek - Zdefiniowanie klasy implementującej interfejs, który zawiera 2 informacje o utworach

public class BasicTrackOperations implements TrackOperations {
    private final Track track;

    public BasicTrackOperations(Track track) {
        this.track = track;
    }

    @Override
    public String getBasicInfo() {
        return "Basic Info: " + track.getName() + " by " + track.getArtists();
    }
}

//Tydzień 2. Wzorzec Bridge Koniec