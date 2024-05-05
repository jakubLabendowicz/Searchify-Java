package bp.PAI_jwt.composite;

//Tydzień 2, Wzorzec Composite
public class PrintText implements Text {
    private Text headerText;
    private Text mainText;
    private Text footerText;

    public PrintText(Text headerText, Text mainText, Text footerText) {
        this.headerText = headerText;
        this.mainText = mainText;
        this.footerText = footerText;
    }

    @Override
    public void print() {
        headerText.print();
        mainText.print();
        footerText.print();
    }
}
//Koniec, Tydzień 2, Wzorzec Composite