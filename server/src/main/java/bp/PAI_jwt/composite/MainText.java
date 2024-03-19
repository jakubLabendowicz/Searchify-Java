package bp.PAI_jwt.composite;

//Tydzień 2, Wzorzec Composite
public class MainText extends BaseText {

    public MainText() {
        super("");
    }

    public MainText(String content) {
        super(content);
    }

    @Override
    public void print() {
        System.out.println(getContent());
    }
}
//Koniec, Tydzień 2, Wzorzec Composite

