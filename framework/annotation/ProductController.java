package framework.annotation;

@UrlMapping(url = "https://example.com/api/products")
public class ProductController {

    @UrlMapping(url = "/all")
    public void getAllProducts() {
        System.out.println("Liste des produits");
    }

    @UrlMapping(url = "/delete")
    public void deleteProduct() {
        System.out.println("Suppression d’un produit");
    }
}
