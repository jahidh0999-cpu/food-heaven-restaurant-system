package fhrms.entity;
import java.math.BigDecimal; import java.time.LocalDateTime;
public class Payment {
    public enum Method { CASH, CARD }
    private final int orderId; private final BigDecimal amount; private final Method method; private final LocalDateTime paidAt;
    public Payment(int orderId, BigDecimal amount, Method method){ this.orderId=orderId; this.amount=amount; this.method=method; this.paidAt=LocalDateTime.now(); }
    public int getOrderId(){return orderId;} public BigDecimal getAmount(){return amount;} public Method getMethod(){return method;} public LocalDateTime getPaidAt(){return paidAt;}
    public String toString(){ return "Payment £"+amount+" via "+method+" at "+paidAt; }
}