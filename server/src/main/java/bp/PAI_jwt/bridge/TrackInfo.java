package bp.PAI_jwt.bridge;

import bp.PAI_jwt.model.Track;

//Tydzień 2. Wzorzec Bridge Początek - Zdefiniowanie klasy abstrakcyjnej implementującej interfejs, który zawiera 2 informacje o utworach
public abstract class TrackInfo {
    protected Track track;

    public TrackInfo(Track track) {
        this.track = track;
    }

    public abstract String getInfo();
}
//Tydzień 2. Wzorzec Bridge Koniec