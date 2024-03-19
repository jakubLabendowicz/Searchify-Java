package bp.PAI_jwt.adapter;

import bp.PAI_jwt.dto.UserDTO;
import bp.PAI_jwt.model.User;

//Tydzień 2, Wzorzec Adapter
// Klasa UserDTOAdapter jest adapterem, który konwertuje obiekty typu UserDTO na obiekty typu User poprzez implementację metody clone()
// w celu zapewnienia zgodności z wymaganiami istniejących struktur.
public class UserDTOAdapter extends User {
    private UserDTO userDTO;

    public UserDTOAdapter(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public Object clone() {
        return new User(userDTO.firstname, userDTO.lastname, userDTO.username, userDTO.password);
    }
}
//Koniec, Tydzień 2, Wzorzec Adapter
