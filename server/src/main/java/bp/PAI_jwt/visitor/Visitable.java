package bp.PAI_jwt.visitor;

// Tydzień 6, Wzorzec Visitor
// Interfejs `Visitable` definiuje metodę `accept`, która umożliwia obiektom akceptowanie odwiedzającego `Visitor`.
// Kod ten stanowi część implementacji wzorca projektowego "Visitor", gdzie obiekty, które chcą być odwiedzane, implementują ten interfejs, co pozwala na ich interakcję z różnymi rodzajami odwiedzających.
public interface Visitable {
    void accept(Visitor visitor);
}
//Koniec, Tydzień 6, Wzorzec Visitor