package fhrms.entity;
import java.math.BigDecimal;
public class MenuItem {
    private final int id; private final String name; private final BigDecimal price; private final String category; private int stockQty;
    public MenuItem(int id, String name, BigDecimal price, String category, int stockQty){this.id=id;this.name=name;this.price=price;this.category=category;this.stockQty=stockQty;}
    public int getId(){return id;} public String getName(){return name;} public BigDecimal getPrice(){return price;} public String getCategory(){return category;} public int getStockQty(){return stockQty;} public void setStockQty(int q){this.stockQty=q;}
    public String toString(){return name + " (£" + price + ")";}
}