package bp.PAI_jwt.interpreter;

import bp.PAI_jwt.model.Track;

import java.util.List;

// Tydzień 5, Wzorzec Interpreter
public interface Expression {
    List<Track> interpret(Context context);
}
//Koniec, Tydzień 5, Wzorzec Interpreter
