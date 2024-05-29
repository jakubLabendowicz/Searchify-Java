package bp.PAI_jwt.tests;

import static org.junit.jupiter.api.Assertions.*;

import bp.PAI_jwt.model.User;
import bp.PAI_jwt.visitor.Visitor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UserTest {

    @Test
    void testUserCreationWithAllFields() {
        User user = new User(1L, "John", "Doe", "john_doe", "password123");
        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("John", user.getFirstname());
        assertEquals("Doe", user.getLastname());
        assertEquals("john_doe", user.getUsername());
        assertEquals("password123", user.getPassword());
    }

    @Test
    void testUserCreationWithOnlyUsernameAndPassword() {
        User user = new User(null,null, "john_doe", "password123");
        assertNotNull(user);
        //assertNull(user.getId());
        assertNull(user.getFirstname());
        assertNull(user.getLastname());
        assertEquals("john_doe", user.getUsername());
        assertEquals("password123", user.getPassword());
    }

    @Test
    void testUserClone() {
        User originalUser = new User(1L, "John", "Doe", "john_doe", "password123");
        User clonedUser = (User) originalUser.clone();
        assertNotSame(originalUser, clonedUser);
        assertEquals(originalUser.getId(), clonedUser.getId());
        assertEquals(originalUser.getFirstname(), clonedUser.getFirstname());
        assertEquals(originalUser.getLastname(), clonedUser.getLastname());
        assertEquals(originalUser.getUsername(), clonedUser.getUsername());
        assertEquals(originalUser.getPassword(), clonedUser.getPassword());
    }

    @Test
    void testUserAcceptVisitor() {
        // Define a mock visitor
        Visitor mockVisitor = Mockito.mock(Visitor.class);

        User user = new User(1L, "John", "Doe", "john_doe", "password123");
        user.accept(mockVisitor);

        // Verify that the visit method of the mock visitor was called with the user object
        Mockito.verify(mockVisitor, Mockito.times(1)).visit(user);
    }

    @Test
    void testUserAcceptVisitorWithNullVisitor() {
        User user = new User(1L, "John", "Doe", "john_doe", "password123");
        assertThrows(NullPointerException.class, () -> user.accept(null));
    }
}
