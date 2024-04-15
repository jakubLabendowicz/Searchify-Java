package bp.PAI_jwt.visitor;

import bp.PAI_jwt.model.Track;
import bp.PAI_jwt.model.User;

// Tydzień 6, Wzorzec Visitor
// Interfejs `Visitor` definiuje metody `visit`, które umożliwiają odwiedzanie obiektów typu `User` i `Track`.
// Ten kod implementuje wzorzec projektowy "Visitor", który pozwala na definiowanie nowych operacji do wykonania na strukturze obiektów, nie zmieniając ich klas.
public interface Visitor {
    void visit(User user);
    void visit(Track track);
}
//Koniec, Tydzień 6, Wzorzec Visitor

