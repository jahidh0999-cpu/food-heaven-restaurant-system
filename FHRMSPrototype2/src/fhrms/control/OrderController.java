package fhrms.control;
import fhrms.entity.*; import java.math.BigDecimal; import java.text.NumberFormat; import java.util.Locale;
public class OrderController {
    private Order currentOrder;
    public void startNewOrder(Table table){ currentOrder=new Order(table); }
    public Order getCurrentOrder(){ return currentOrder; }
    public void addItem(MenuItem item, int qty){ ensureOrder(); currentOrder.addItem(item, qty); }
    public BigDecimal getSubtotal(){ ensureOrder(); return currentOrder.total(); }
    public String getSubtotalCurrency(){ return NumberFormat.getCurrencyInstance(Locale.UK).format(getSubtotal()); }
    public Order confirm(){ ensureOrder(); currentOrder.setStatus(Order.Status.PLACED); return currentOrder; }
    private void ensureOrder(){ if(currentOrder==null){ startNewOrder(new Table(1,4, Table.TableStatus.OCCUPIED)); } }
}