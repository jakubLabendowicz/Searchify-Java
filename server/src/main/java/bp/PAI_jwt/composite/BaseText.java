package bp.PAI_jwt.composite;

//Tydzień 2, Wzorzec Composite
abstract class BaseText implements Text {
    private String content;

    public BaseText(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
//Koniec, Tydzień 2, Wzorzec Composite