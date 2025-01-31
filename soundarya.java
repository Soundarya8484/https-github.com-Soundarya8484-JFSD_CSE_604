import java.util.*;

// Abstract Product Class
abstract class Product {
    protected int productId;
    protected String name;
    protected double price;
    protected int stock;

    public Product(int productId, String name, double price, int stock) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public abstract void displayProduct();

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

// Electronics Class (inherits from Product)
class Electronics extends Product {
    private String brand;

    public Electronics(int productId, String name, double price, int stock, String brand) {
        super(productId, name, price, stock);
        this.brand = brand;
    }

    @Override
    public void displayProduct() {
        System.out.println(name + " (" + brand + ") - Price: $" + price + " - Stock: " + stock);
    }

    public String getBrand() {
        return brand;
    }
}

// Clothing Class (inherits from Product)
class Clothing extends Product {
    private String size;

    public Clothing(int productId, String name, double price, int stock, String size) {
        super(productId, name, price, stock);
        this.size = size;
    }

    @Override
    public void displayProduct() {
        System.out.println(name + " (Size: " + size + ") - Price: $" + price + " - Stock: " + stock);
    }

    public String getSize() {
        return size;
    }
}

// Footwear Class (inherits from Product)
class Footwear extends Product {
    private String size;

    public Footwear(int productId, String name, double price, int stock, String size) {
        super(productId, name, price, stock);
        this.size = size;
    }

    @Override
    public void displayProduct() {
        System.out.println(name + " (Size: " + size + ") - Price: $" + price + " - Stock: " + stock);
    }

    public String getSize() {
        return size;
    }
}

// Stationary Class (inherits from Product)
class Stationary extends Product {
    private String type;

    public Stationary(int productId, String name, double price, int stock, String type) {
        super(productId, name, price, stock);
        this.type = type;
    }

    @Override
    public void displayProduct() {
        System.out.println(name + " (" + type + ") - Price: $" + price + " - Stock: " + stock);
    }

    public String getType() {
        return type;
    }
}

// OnlineStore Class to manage inventory and cart
class OnlineStore {
    private Map<Integer, Product> inventory = new HashMap<>();
    private List<CartItem> cart = new ArrayList<>();

    // Add product to inventory
    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
    }

    // View all products in inventory
    public void viewInventory() {
        if (inventory.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("\nAvailable Products:");
            for (Product product : inventory.values()) {
                product.displayProduct();
            }
        }
    }

    // Add product to shopping cart
    public void addToCart(int productId, int quantity) {
        Product product = inventory.get(productId);
        if (product != null) {
            if (product.getStock() >= quantity) {
                cart.add(new CartItem(product, quantity));
                product.setStock(product.getStock() - quantity);
                System.out.println(quantity + " " + product.getName() + "(s) added to the cart.");
            } else {
                System.out.println("Insufficient stock for " + product.getName());
            }
        } else {
            System.out.println("Product not found.");
        }
    }

    // View shopping cart
    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("\nYour Cart:");
            for (CartItem item : cart) {
                System.out.println(item.getProduct().getName() + " - " + item.getQuantity() + " x $" + item.getProduct().getPrice() + " each");
            }
        }
    }

    // Checkout and display total cost
    public void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            double total = 0;
            System.out.println("\nCheckout Summary:");
            for (CartItem item : cart) {
                System.out.println(item.getProduct().getName() + " - " + item.getQuantity() + " x $" + item.getProduct().getPrice());
                total += item.getProduct().getPrice() * item.getQuantity();
            }
            System.out.println("Total: $" + total);
            cart.clear();
        }
    }

    // CartItem class to hold product and quantity in cart
    private static class CartItem {
        private Product product;
        private int quantity;

        public CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }
    }
}

// Main Class (User Interface)
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OnlineStore store = new OnlineStore();

        // Adding some products to the store
        Electronics laptop = new Electronics(101, "Laptop", 1200.0, 10, "Dell");
        Electronics smartphone = new Electronics(102, "Smartphone", 800.0, 20, "Samsung");
        Clothing tshirt = new Clothing(201, "T-Shirt", 25.0, 50, "M");
        Clothing jeans = new Clothing(202, "Jeans", 45.0, 30, "L");
        Footwear sneakers = new Footwear(301, "Sneakers", 60.0, 40, "42");
        Footwear sandals = new Footwear(302, "Sandals", 25.0, 25, "38");
        Stationary notebook = new Stationary(401, "Notebook", 5.0, 100, "Writing");
        Stationary pen = new Stationary(402, "Pen", 1.5, 200, "Writing");

        store.addProduct(laptop);
        store.addProduct(smartphone);
        store.addProduct(tshirt);
        store.addProduct(jeans);
        store.addProduct(sneakers);
        store.addProduct(sandals);
        store.addProduct(notebook);
        store.addProduct(pen);

        while (true) {
            // User Menu
            System.out.println("\nWelcome to the Online Store!");
            System.out.println("1. View Inventory");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    store.viewInventory();  // Display all products in inventory
                    break;

                case 2:
                    // Add product to cart
                    System.out.print("Enter product ID to add to cart: ");
                    int productId = scanner.nextInt();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    store.addToCart(productId, quantity);
                    break;

                case 3:
                    store.viewCart();  // Display cart items
                    break;

                case 4:
                    store.checkout();  // Checkout and display total amount
                    break;

                case 5:
                    System.out.println("Thank you for shopping!");  // Exit the program
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}