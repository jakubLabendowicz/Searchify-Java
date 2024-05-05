package bp.PAI_jwt.visitor;

import bp.PAI_jwt.model.Track;
import bp.PAI_jwt.model.User;

// Tydzień 6, Wzorzec Visitor
// Klasa `ElementVisitor` implementuje interfejs `Visitor`, definiując metody `visit`, które wypisują nazwy odwiedzanych użytkowników i utworów.
// Ten kod reprezentuje konkretne zachowanie odwiedzającego w kontekście wzorca Visitor, gdzie różne typy obiektów są obsługiwane przez odpowiednie metody.
public class ElementVisitor implements Visitor {
    @Override
    public void visit(User user) {
        System.out.println("Visiting user: " + user.getUsername());
    }

    @Override
    public void visit(Track track) {
        System.out.println("Visiting track: " + track.getName());
    }
}
//Koniec, Tydzień 6, Wzorzec Visitor