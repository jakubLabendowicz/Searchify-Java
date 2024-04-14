package bp.PAI_jwt.visitor;

import bp.PAI_jwt.model.Track;
import bp.PAI_jwt.model.User;

public interface Visitor {
    void visit(User user);
    void visit(Track track);
}

