package bp.PAI_jwt.model;

import javax.persistence.*;

import bp.PAI_jwt.prototype.Cloneable;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;

@Entity
@Table(name = "users")
public class User implements Cloneable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String username;

    @Column
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Favorite> favorites;


    public User() {
    }

    public User(String firstname, String lastname, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    public User(long id, String firstname, String lastname, String username, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Tydzień 1, Wzorzec Prototype
    //Interfejs Cloneable definiuje metodę clone() umożliwiającą tworzenie kopii obiektów.
    // Klasa User implementuje ten interfejs, pozwalając na klonowanie użytkowników.
    @Override
    public Object clone() {
        return new User(id, firstname, lastname, username, password);
    }
    //Koniec, Tydzień 1, Wzorzec Prototype
}
