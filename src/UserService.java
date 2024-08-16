import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    private List<User> users = new ArrayList<>();

    // Create a new user
    public void createUser(String username, String password, boolean active) {
        User user = new User(username, password, active);
        users.add(user);
    }

    // Read (Retrieve) a user by username
    public User getUser(String username) {
        Optional<User> user = users.stream()
                                   .filter(u -> u.getUsername().equals(username))
                                   .findFirst();
        return user.orElse(null);
    }

    // Update a user's information
    public boolean updateUser(String username, String newPassword, boolean newActiveStatus) {
        User user = getUser(username);
        if (user != null) {
            user.setPassword(newPassword);
            user.setActive(newActiveStatus);
            return true;
        }
        return false;
    }

    // Delete a user by username
    public boolean deleteUser(String username) {
        return users.removeIf(user -> user.getUsername().equals(username));
    }

    // List all users
    public List<User> listUsers() {
        return users;
    }
    
    public static void main(String[] args) {
        UserService userService = new UserService();

        // Creating users
        userService.createUser("john_doe", "password123", true);
        userService.createUser("jane_smith", "qwerty456", false);

        // Reading a user
        User user = userService.getUser("Niyas");
        System.out.println(user);

        // Updating a user
        userService.updateUser("Niyas", "new_password", true);

        // Deleting a user
        userService.deleteUser("Niyas");

        // Listing all users
        userService.listUsers().forEach(System.out::println);
    }
}