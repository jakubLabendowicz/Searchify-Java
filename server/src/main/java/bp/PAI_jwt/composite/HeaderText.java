package bp.PAI_jwt.composite;

//Tydzień 2, Wzorzec Composite
public class HeaderText extends BaseText {

    public HeaderText() {
        super("");
    }

    public HeaderText(String content) {
        super(content);
    }

    @Override
    public void print() {
        System.out.println("##### ##### ##### ##### #####");
        System.out.println(getContent());
    }
}
//Koniec, Tydzień 2, Wzorzec Composite