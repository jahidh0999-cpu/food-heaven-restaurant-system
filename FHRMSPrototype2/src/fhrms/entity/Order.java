package fhrms.entity;
import java.math.BigDecimal; import java.time.LocalDateTime; import java.util.*;
public class Order {
    private static int NEXT_ID=100; public enum Status { NEW, PLACED, BILLED, PAID }
    private final int id; private final LocalDateTime createdAt; private final List<OrderItem> items=new ArrayList<>(); private Status status=Status.NEW; private Table table;
    public Order(Table table){ this.id=NEXT_ID++; this.createdAt=LocalDateTime.now(); this.table=table; }
    public int getId(){return id;} public LocalDateTime getCreatedAt(){return createdAt;} public List<OrderItem> getItems(){return items;} public Status getStatus(){return status;} public void setStatus(Status s){this.status=s;} public Table getTable(){return table;}
    public void addItem(MenuItem item, int qty){ items.add(new OrderItem(item, qty)); }
    public BigDecimal total(){ return items.stream().map(OrderItem::lineTotal).reduce(BigDecimal.ZERO, BigDecimal::add); }
    public String toString(){ return "Order #"+id+" ("+status+") total=£"+total(); }
}