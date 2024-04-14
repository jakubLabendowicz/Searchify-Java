package bp.PAI_jwt.interpreter;

import bp.PAI_jwt.model.Track;

import java.util.List;

// Tydzień 5, Wzorzec Interpreter
public class NameExpression implements Expression {
    private String name;

    public NameExpression(String name) {
        this.name = name;
    }

    @Override
    public List<Track> interpret(Context context) {
        // Zwróć listę ulubionych utworów użytkownika, które pasują do podanej nazwy
        return context.getFavoritesByName(name);
    }
}
//Koniec, Tydzień 5, Wzorzec Interpreter

