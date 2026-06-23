package dev.ellesh.fakestorerestclient.models;
import java.util.List;

public class Cart {
    private int id;
    private int userId;
    private String date;
    private List<CartItem> products;

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public List<CartItem> getProducts() { return products; }
    public void setProducts(List<CartItem> products) { this.products = products; }

    public static class CartItem {
        private int productId;
        private int quantity;

        public int getProductId() { return productId; }
        public void setProductId(int productId) { this.productId = productId; }

        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }
}
