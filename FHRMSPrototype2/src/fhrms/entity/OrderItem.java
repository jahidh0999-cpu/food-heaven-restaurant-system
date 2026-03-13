package fhrms.entity;
import java.math.BigDecimal;
public class OrderItem {
    private final MenuItem item; private int quantity;
    public OrderItem(MenuItem item, int quantity){ if(quantity<=0) throw new IllegalArgumentException("Quantity must be positive"); this.item=item; this.quantity=quantity; }
    public MenuItem getItem(){return item;} public int getQuantity(){return quantity;} public void setQuantity(int q){ if(q>0) this.quantity=q; }
    public BigDecimal lineTotal(){ return item.getPrice().multiply(BigDecimal.valueOf(quantity)); }
    public String toString(){ return quantity + " x " + item.getName() + " = £" + lineTotal(); }
}