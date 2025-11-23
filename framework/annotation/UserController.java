package framework.annotation;

@UrlMapping(url = "https://example.com/api/users")
public class UserController {

    @UrlMapping(url = "/list")
    public String[] listUsers() {
        System.out.println("Liste des utilisateurs");
        return new String[]{"user1", "user2", "user3"};
    }

    @UrlMapping(url = "/add")
    public boolean addUser() {
        System.out.println("Ajout d'un utilisateur");
        return true; // Return success status
    }
}
