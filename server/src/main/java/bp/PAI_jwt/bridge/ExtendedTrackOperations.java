package bp.PAI_jwt.bridge;

import bp.PAI_jwt.model.Track;

//Tydzień 2. Wzorzec Bridge Początek - Zdefiniowanie klasy implementującej interfejs, który zawiera 3 informacje o utworach
public class ExtendedTrackOperations implements TrackOperations {
    private final Track track;

    public ExtendedTrackOperations(Track track) {
        this.track = track;
    }

    @Override
    public String getBasicInfo() {
        return "Extended Info: " + track.getName() + " by " + track.getArtists() + ", Popularity: " + track.getPopularity();
    }
}
//Tydzień 2. Wzorzec Bridge Koniec