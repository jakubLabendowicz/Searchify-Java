package bp.PAI_jwt.model;


import javax.persistence.Entity;
import javax.persistence.Table;

// Tydzień 8 Początek. Zasada podstawienia Liskov, W tym przykładzie User, AdminUser i GuestUser
// są używane zamiennie jako obiekty klasy bazowej User.
@Entity
@Table(name = "guest_users")
public class GuestUser extends User {
    private boolean isTemporary;

    public GuestUser() {
        super();
    }

    public GuestUser(String firstname, String lastname, String username, String password, boolean isTemporary) {
        super(firstname, lastname, username, password);
        this.isTemporary = isTemporary;
    }

    public boolean isTemporary() {
        return isTemporary;
    }

    public void setTemporary(boolean temporary) {
        isTemporary = temporary;
    }
}

// Tydzień 8 Koniec. Zasada podstawienia Liskov.