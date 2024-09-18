import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Storage {
    private List<Product> products;

    public Storage() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        if(product == null) return;
        products.add(product);
    }

    public Product findProductById(int id) {
        for (Product product : products) {
            if(product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public List<Product> findProductByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if(product.getName().equals(name)) {
                result.add(product);
            }
        }
        return result;
    }

    public List<Product> findProductByCategory(String categoryName) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if(product.getCategory().getName().equals(categoryName)) {
                result.add(product);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Товари{" +  products + '}';
    }
}
