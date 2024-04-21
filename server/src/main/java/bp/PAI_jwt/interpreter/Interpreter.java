package bp.PAI_jwt.interpreter;

import bp.PAI_jwt.model.Track;

import java.util.List;

// Tydzień 5, Wzorzec Interpreter
public class Interpreter {
    private Expression expression;

    public Interpreter(Expression expression) {
        this.expression = expression;
    }

    public List<Track> interpret(Context context) {
        return expression.interpret(context);
    }
}
//Koniec, Tydzień 5, Wzorzec Interpreter