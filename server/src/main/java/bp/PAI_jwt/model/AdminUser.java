package bp.PAI_jwt.model;

import javax.persistence.Entity;
import javax.persistence.Table;
// Tydzień 8 Początek. Zasada podstawienia Liskov, W tym przykładzie User, AdminUser i GuestUser
// są używane zamiennie jako obiekty klasy bazowej User.
@Entity
@Table(name = "admin_users")
public class AdminUser extends User {

    private String role;

    public AdminUser() {
        super();
    }

    public AdminUser(String firstname, String lastname, String username, String password, String role) {
        super(firstname, lastname, username, password);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
// Tydzień 8 Koniec. Zasada podstawienia Liskov.