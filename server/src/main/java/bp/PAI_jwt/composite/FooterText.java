package bp.PAI_jwt.composite;

//Tydzień 2, Wzorzec Composite
public class FooterText extends BaseText {

    public FooterText() {
        super("");
    }

    public FooterText(String content) {
        super(content);
    }

    @Override
    public void print() {
        System.out.println(getContent());
        System.out.println("##### ##### ##### ##### #####");
    }
}
//Koniec, Tydzień 2, Wzorzec Composite