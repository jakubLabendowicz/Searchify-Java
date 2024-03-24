package bp.PAI_jwt.decorator;

import bp.PAI_jwt.model.Track;
// Tydzień 2. Wzorzec Decorator Początek - zastosowany wzorzec w tej klasie ma na celu udekorowanie obiektu Track nadajac mu mozliwosc
// wyswietlenia informacji o utworze, nie modyfikujac przy tym metod w klasie bazowej
public abstract class TrackDecorator implements Cloneable {
    protected Track decoratedTrack;

    public TrackDecorator(Track decoratedTrack) {
        this.decoratedTrack = decoratedTrack;
    }

    public abstract String displayInfo();

    @Override
    public Object clone() throws CloneNotSupportedException {
        TrackDecorator clonedDecorator = (TrackDecorator) super.clone();
        // Skopiuj dekorowany obiekt
        clonedDecorator.decoratedTrack = (Track) decoratedTrack.clone();
        return clonedDecorator;
    }
}
// Tydzień 2. Wzorzec Decorator Koniec