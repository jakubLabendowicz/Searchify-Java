package bp.PAI_jwt.interpreter;

import bp.PAI_jwt.model.Track;

import java.util.List;

// Tydzień 5, Wzorzec Interpreter
public class AndExpression implements Expression {
    private Expression expression1;
    private Expression expression2;

    public AndExpression(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public List<Track> interpret(Context context) {
        // Połącz wyniki interpretacji dwóch wyrażeń za pomocą operatora AND
        List<Track> result1 = expression1.interpret(context);
        List<Track> result2 = expression2.interpret(context);
        result1.retainAll(result2); // Pozostaw tylko wspólne utwory
        return result1;
    }
}
//Koniec, Tydzień 5, Wzorzec Interpreter