package bp.PAI_jwt.decorator;

import bp.PAI_jwt.model.Track;
//Tydzień 2. Wzorzec Decorator Początek - Zdefiniowanie metody wyświetlającej informacje dla kategorii dla dekoratora obiektu
public class CategoryTrackDecorator extends TrackDecorator {
    private String category;

    public CategoryTrackDecorator(Track decoratedTrack, String category) {
        super(decoratedTrack);
        this.category = category;
    }

    @Override
    public String displayInfo() {
        return this.displayInfo() + ", Category: " + category;
    }
}
//Tydzień 2. Wzorzec Decorator Koniec