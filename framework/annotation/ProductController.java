package framework.annotation;

@UrlMapping(url = "https://example.com/api/products")
public class ProductController {

    @UrlMapping(url = "/all")
    public String getAllProducts() {
        System.out.println("Liste des produits");
        return "Liste des produits";
    }

    @UrlMapping(url = "/delete")
    public int deleteProduct() {
        System.out.println("Suppression d’un produit");
        return 1; // Return status code
    }
}