package bp.PAI_jwt.visitor;

import bp.PAI_jwt.model.Track;
import bp.PAI_jwt.model.User;

public class ElementVisitor implements Visitor {
    @Override
    public void visit(User user) {
        System.out.println("Visiting user: " + user.getUsername());
    }

    @Override
    public void visit(Track track) {
        System.out.println("Visiting track: " + track.getName());
    }
}

