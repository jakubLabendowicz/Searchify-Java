package bp.PAI_jwt.decorator;

import bp.PAI_jwt.model.Track;
//Tydzień 2. Wzorzec Decorator Początek - Zdefiniowanie metody wyświetlającej dodatkowe informacji dla dekorowanego utworu
public class ExtendedTrackInfoDecorator extends TrackDecorator {
    private String additionalInfo;

    public ExtendedTrackInfoDecorator(Track decoratedTrack, String additionalInfo) {
        super(decoratedTrack);
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String displayInfo() {
        // Zwróć informacje o utworze wraz z dodatkowymi informacjami
        return decoratedTrack.toString() + " \n Additional Info: " + additionalInfo;
    }
}
//Tydzień 2. Wzorzec Decorator Koniec