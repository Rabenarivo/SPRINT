package framework.annotation;

@UrlMapping(url = "https://example.com/api/users")
public class UserController {

    @UrlMapping(url = "/list")
    public void listUsers() {
        System.out.println("Liste des utilisateurs");
    }

    @UrlMapping(url = "/add")
    public void addUser() {
        System.out.println("Ajout d’un utilisateur");
    }
}
